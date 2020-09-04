package com.github.haliibobo.learn.distributed.lock;

import com.github.haliibobo.learn.distributed.lock.exception.LockLeaseLostException;
import java.util.concurrent.TimeUnit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-09-03 10:35
 * @description describe what this class do
 */
public interface Lock {

    /**
     * 阻塞获取锁 打断异常 连接异常
     */

    void lock() throws InterruptedException;
    /**
     * 阻塞一定时间获取锁 打断异常 连接异常
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * 非阻塞获得锁 连接异常
     * @return
     */
    boolean tryLock();

    /**
     * 释放锁 连接异常 失去锁异常
     */
    void unlock() throws LockLeaseLostException;

    /**
     * 强制释放锁 连接异常 失去锁异常
     */
    void forceUnLock();

    /**
     * 续租
     */
     boolean KeepLockLease() throws LockLeaseLostException;

}
