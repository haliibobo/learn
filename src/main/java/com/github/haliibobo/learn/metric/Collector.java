package com.github.haliibobo.learn.metric;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Collector {

    private static final Collector INSTANCE = new Collector();
    private static final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private static volatile Map<String, Summary> summarys = new ConcurrentHashMap<>();

    static {
        long initialDelay = 1000 - (System.currentTimeMillis() % 1000);
        scheduledExecutor.scheduleAtFixedRate(new CollectorTask(), initialDelay, 1000, TimeUnit.MILLISECONDS);
    }

    public static Collector getInstance() {
        return INSTANCE;
    }

    public Summary getHealthCountSummary(String key) {
        Summary summary = summarys.get(key);
        if (summary == null) {
            summary = new Summary(0, 0, 0);
        }
        return summary;
    }

    private static class CollectorTask implements Runnable {
        @Override
        public void run() {
            Map<String, Bucket> buckets = Metric.getBuckets();
            LocalDateTime lcDateTime = LocalDateTime.now();
            long currTm = lcDateTime.atZone(ZoneId.systemDefault()).getSecond();
            int index = (int)(currTm) % 100;
            Map<String, Summary> summaryCounts0 = new ConcurrentHashMap<>();

            for (String key : buckets.keySet()) {
                Bucket bucket = buckets.get(key);
                int rollingStatsTime = bucket.getRollingStatsTime();
                long success = 0, fail = 0;

                /*累计前 checkTime 秒的数据*/
                for (int i = 1; i <= rollingStatsTime; i++) {
                    int preIndex = index - i;
                    preIndex = preIndex >= 0 ? preIndex : preIndex + 100;
                    Health health = bucket.get(preIndex);
                    success += health.getSuccessCount();
                    fail += health.getErrorCount();
                }
                summaryCounts0.put(key, new Summary(success, fail, (int)(success + fail) / rollingStatsTime));

                /*清空之前的数据*/
                for (int i = rollingStatsTime + 1, j = rollingStatsTime + 2; i < j; i++) {
                    int preIndex = index - i;
                    preIndex = preIndex >= 0 ? preIndex : preIndex + 100;
                    Health health0 = bucket.get(preIndex);
                    health0.clear();
                }
            }
            summarys = summaryCounts0;
        }
    }
}
