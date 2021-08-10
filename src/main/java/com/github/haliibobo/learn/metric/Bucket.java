package com.github.haliibobo.learn.metric;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Bucket {

    private static final int NUM = 100;
    private Health[] health = new Health[NUM];
    private int rollingStatsTime;


    Bucket(int rollingStatsTime) {
        this.rollingStatsTime = rollingStatsTime;

        for (int i = 0; i < NUM; i++) {
            health[i] = new Health();
        }
    }

    void mark(boolean success) {
        int index = (int)(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()) % 100;
        Health health = this.health[index];
        if (success) {
            health.markSuccess();
        } else {
            health.markFailed();
        }
    }

    void reset() {
        for (int i = 0; i < NUM; i++) {
            health[i].clear();
        }
    }

    public Health get(int index) {
        return health[index];
    }

    int getRollingStatsTime() {
        return rollingStatsTime;
    }
}
