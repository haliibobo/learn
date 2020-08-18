package com.github.haliibobo.learn.java.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
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
     * tryLock() 使用非公平锁
     */
    public void test() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        /**
         * 公平锁 直接  1.acquire(1)
         * 非公平锁 首先会进行 cas操作   0. compareAndSetState(0, 1) 然后  2.acquire(1)
         * acquire(1) 操作
         * 3. 首先 3.1 tryAcquire(1)  公平锁 首先 state == 0 检测是否有前驱，没有使用cas，
         * 非公平锁 直接调用nonfairTryAcquire(1) 首先 state == 0 ,使用cas
         * )
         * 3.2 tryAcquire(1) 失败，开始排队
         * 3.3 acquireQueued(addWaiter(Node.EXCLUSIVE), 1)；
         * 3.4 addWaiter(Node.EXCLUSIVE)
         * 3.4.1 tail != null (  compareAndSetTail  )
         *3.4.2 tail == null (  enq(node)  )
         * 3.4.2.1 tail == null (compareAndSetHead(new Node())) 失败后 tail != null  compareAndSetTail
         */
        lock.lock();
        /**
         * tryLock 无法实现公平锁，直接调用 nonfairTryAcquire(1)
         */
        lock.tryLock();
        /**
         * 调用 sync.tryAcquireNanos(1, unit.toNanos(timeout))
         * 1。首先调用 tryAcquire（1）
         */
        lock.tryLock(1000, TimeUnit.MICROSECONDS);
        lock.unlock();
        lock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(1);
    }
}
