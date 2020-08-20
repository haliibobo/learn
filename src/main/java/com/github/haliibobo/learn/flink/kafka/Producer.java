package com.github.haliibobo.learn.flink.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * ./kafka-server-start.sh -daemon ../config/server.properties
 * netstat -anput | grep 9092
 *
 *
 * replication-factor  不得大于可用broker
 * ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic weather --partitions 5 --replication-factor 1
 * ./kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic weather
 *
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic weather
 *
 * http://flash.weather.com.cn/wmaps/xml/china.xml
 * http://www.weather.com.cn/data/cityinfo/101190408.html
 * http://www.weather.com.cn/data/sk/101010100.html
 */
public class Producer extends Thread {
    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final boolean isAsync;

    public Producer(final String topic,
        final boolean isAsync,
        final String transactionalId,
        final boolean enableIdempotency,
        final int transactionTimeoutMs) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "weather-producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        if (transactionTimeoutMs > 0) {
            props.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, transactionTimeoutMs);
        }
        if (transactionalId != null) {
            props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalId);
        }
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotency);

        producer = new KafkaProducer<>(props);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    KafkaProducer<Integer, String> get() {
        return producer;
    }

    @Override
    public void run() {
        int messageKey = 0;
            String messageStr = "Message_" + messageKey;
            long startTime = System.currentTimeMillis();
            if (isAsync) { // Send asynchronously
                producer.send(new ProducerRecord<>(topic,
                    messageKey,
                    messageStr), new WeatherCallBack(startTime, messageKey, messageStr));
            } else { // Send synchronously
                try {
                    producer.send(new ProducerRecord<>(topic,
                        messageKey,
                        messageStr)).get();
                    System.out.println("Sent message: (" + messageKey + ", " + messageStr + ")");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            messageKey += 2;

        System.out.println("Producer sent "  + " records successfully");
    }

    class WeatherCallBack implements Callback {

        private final long startTime;
        private final int key;
        private final String message;

        public WeatherCallBack(long startTime, int key, String message) {
            this.startTime = startTime;
            this.key = key;
            this.message = message;
        }

        /**
         * A callback method the user can implement to provide asynchronous handling of request completion. This method will
         * be called when the record sent to the server has been acknowledged. When exception is not null in the callback,
         * metadata will contain the special -1 value for all fields except for topicPartition, which will be valid.
         *
         * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
         *                  occurred.
         * @param exception The exception thrown during processing of this record. Null if no error occurred.
         */
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (metadata != null) {
                System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                        "), " +
                        "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
            } else {
                exception.printStackTrace();
            }
        }
    }
}


