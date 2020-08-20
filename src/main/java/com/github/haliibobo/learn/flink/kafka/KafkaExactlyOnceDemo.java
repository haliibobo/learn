package com.github.haliibobo.learn.flink.kafka;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.errors.UnknownTopicOrPartitionException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class KafkaExactlyOnceDemo {

    private static final String INPUT_TOPIC = "input-topic";
    private static final String OUTPUT_TOPIC = "output-topic";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Should accept 3 parameters: " +
                "[number of partitions], [number of instances], [number of records]");
        }

        int numPartitions = Integer.parseInt(args[0]);
        int numInstances = Integer.parseInt(args[1]);
        int numRecords = Integer.parseInt(args[2]);

        /* Stage 1: topic cleanup and recreation */
        recreateTopics(numPartitions);

        CountDownLatch prePopulateLatch = new CountDownLatch(1);

        /* Stage 2: pre-populate records */
        Producer producerThread = new Producer(INPUT_TOPIC, false, null, true, -1);
        producerThread.start();

        if (!prePopulateLatch.await(5, TimeUnit.MINUTES)) {
            throw new TimeoutException("Timeout after 5 minutes waiting for data pre-population");
        }

        CountDownLatch transactionalCopyLatch = new CountDownLatch(numInstances);

        /* Stage 3: transactionally process all messages */
        for (int instanceIdx = 0; instanceIdx < numInstances; instanceIdx++) {
            ExactlyOnceMessageProcessor messageProcessor = new ExactlyOnceMessageProcessor(
                INPUT_TOPIC, OUTPUT_TOPIC, instanceIdx, transactionalCopyLatch);
            messageProcessor.start();
        }

        if (!transactionalCopyLatch.await(5, TimeUnit.MINUTES)) {
            throw new TimeoutException("Timeout after 5 minutes waiting for transactionally message copy");
        }

        CountDownLatch consumeLatch = new CountDownLatch(1);

        /* Stage 4: consume all processed messages to verify exactly once */
        Consumer consumerThread = new Consumer(OUTPUT_TOPIC, "Verify-consumer", Optional.empty(), true, numRecords, consumeLatch);
        consumerThread.start();

        if (!consumeLatch.await(5, TimeUnit.MINUTES)) {
            throw new TimeoutException("Timeout after 5 minutes waiting for output data consumption");
        }

        consumerThread.shutdown();
        System.out.println("All finished!");
    }

    private static void recreateTopics(final int numPartitions)
        throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);

        Admin adminClient = Admin.create(props);

        List<String> topicsToDelete = Arrays.asList(INPUT_TOPIC, OUTPUT_TOPIC);

        deleteTopic(adminClient, topicsToDelete);

        // Check topic existence in a retry loop
        while (true) {
            System.out.println("Making sure the topics are deleted successfully: " + topicsToDelete);

            Set<String> listedTopics = adminClient.listTopics().names().get();
            System.out.println("Current list of topics: " + listedTopics);

            boolean hasTopicInfo = false;
            for (String listedTopic : listedTopics) {
                if (topicsToDelete.contains(listedTopic)) {
                    hasTopicInfo = true;
                    break;
                }
            }
            if (!hasTopicInfo) {
                break;
            }
            Thread.sleep(1000);
        }

        // Create topics in a retry loop
        while (true) {
            final short replicationFactor = 1;
            final List<NewTopic> newTopics = Arrays.asList(
                new NewTopic(INPUT_TOPIC, numPartitions, replicationFactor),
                new NewTopic(OUTPUT_TOPIC, numPartitions, replicationFactor));
            try {
                adminClient.createTopics(newTopics).all().get();
                System.out.println("Created new topics: " + newTopics);
                break;
            } catch (ExecutionException e) {
                if (!(e.getCause() instanceof TopicExistsException)) {
                    throw e;
                }
                System.out.println("Metadata of the old topics are not cleared yet...");

                deleteTopic(adminClient, topicsToDelete);

                Thread.sleep(1000);
            }
        }
    }

    private static void deleteTopic(final Admin adminClient, final List<String> topicsToDelete)
        throws InterruptedException, ExecutionException {
        try {
            adminClient.deleteTopics(topicsToDelete).all().get();
        } catch (ExecutionException e) {
            if (!(e.getCause() instanceof UnknownTopicOrPartitionException)) {
                throw e;
            }
            System.out.println("Encountered exception during topic deletion: " + e.getCause());
        }
        System.out.println("Deleted old topics: " + topicsToDelete);
    }
}
