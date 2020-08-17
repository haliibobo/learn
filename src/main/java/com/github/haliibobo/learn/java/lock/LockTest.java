package com.github.haliibobo.learn.java.lock;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-17 17:51
 * @description describe what this class do
 */
public class LockTest {




    @Test
    /**
     * Uses AQS state(cas change state when state == 0) to
     *represent the number of holds on the lock
     *
     *非公平锁 在lock的时候 会首先使用cas ，失败后加入排队
     * 公平锁 直接加入排队
     * 非公平锁 在 tryAcquire 时 会使用cas
     * 公平锁 在 tryAcquire 时 首先判断没有前驱者，才会使用cas
     */
    public void test(){

    }
}
