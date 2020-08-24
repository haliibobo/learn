package com.github.haliibobo.learn.flink.kafka;


/**
 * [Producer clientId=weather-producer] Error connecting to node iZ2ze8nyz28va0yibx59hxZ:9092 (id: 0 rack: null)
 * java.net.UnknownHostException: iZ2ze8nyz28va0yibx59hxZ: nodename nor servname provided, or not known
 * 阿里云机器 ip别名真坑爹
 */
public class KafkaProperties {
    public static final String TOPIC = "weather";
    public static final String KAFKA_SERVER_URL = "182.92.98.63";
    public static final int KAFKA_SERVER_PORT = 9092;

    private KafkaProperties() {}
}
