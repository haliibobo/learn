package com.github.haliibobo.learn.metric;

import java.util.concurrent.atomic.LongAdder;

class Health {

    private LongAdder success = new LongAdder();
    private LongAdder error= new LongAdder();

    void clear() {
        error.reset();
        success.reset();
    }
    void markFailed() {
        error.increment();
    }

    void markSuccess() {
        success.increment();
    }

    int getErrorCount() {
        return error.intValue();
    }

    int getSuccessCount() {
        return success.intValue();
    }

}
