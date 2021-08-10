package com.github.haliibobo.learn.metric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Metric {

    private static Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    private static Collector collector = Collector.getInstance();
    private String key;
    private Bucket bucket;

    public Metric(String key) {
        this.key = key;
        this.bucket = new Bucket(1);
        buckets.put(key, bucket);
    }

    /**
     * mark fail
     */
    public void markFailed() {
        bucket.mark(false);
    }

    /**
     * mark success
     */
    public void markSuccess() {
        bucket.mark(true);
    }

    /**
     * 清空数据
     */
    public void reset() {
        collector.getHealthCountSummary(key).clear();
        bucket.reset();
    }

    /**
     * 获取当前和统计时间区间的请求统计数据
     * @return
     */
    public Summary getSummary() {
        Summary summaryHealthCount = collector.getHealthCountSummary(key);
        int index = (int)(System.currentTimeMillis() / 1000) % 100;
        Health latestHealth = bucket.get(index);
        return summaryHealthCount.plus(latestHealth);
    }

    public static Map<String, Bucket> getBuckets() {
        return buckets;
    }

    public static class Count {

        private int value;

        Count() {
        }

        public Count(int value) {
            this.value = value;
        }

        public void add(int next) {
            this.value = value + next;
        }

        public int getValue() {
            return value;
        }
    }
}
