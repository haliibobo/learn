package com.github.haliibobo.learn.flink.kafka;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleWithFixedDelay( new WeatherProducer("weather",true),0,1, TimeUnit.HOURS);
    }
}
