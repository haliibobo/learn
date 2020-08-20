package com.github.haliibobo.learn.kuaishou;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsume<T> {

    private int size;
    private int delay;
    private List<T> container;
    private final  int FULL = 10;
    private final  int EMPTY = 0;
    private AtomicInteger count = new AtomicInteger(0);
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private  final  Condition empty = lock.newCondition();
    final ScheduledExecutorService es ;


    public  ProduceConsume (int size,int delay){
        this.size =size;
        this.delay = delay;
        container = new ArrayList<>();
        es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleWithFixedDelay( new Consumer(),delay,delay, TimeUnit.SECONDS);
    }






    @Test
    public void test() throws InterruptedException {
        CountDownLatch c = new CountDownLatch(1);
       ExecutorService es = Executors.newFixedThreadPool(128);
       for (int i = 0;i < 100;i ++){
           es.submit(new Producer());
       }
        for (int i = 0;i < 100;i ++){
            es.submit(new Consumer());
        }
    c.await();
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(500));
                lock.lock();
                while (count.get() == FULL){
                    //生产者阻塞
                    full.await();
                }
                System.out.println(Thread.currentThread().getName()
                        + " 生产者 生产完毕，count " + count.incrementAndGet());
                //唤醒消费者
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放锁
                lock.unlock();
            }
        }
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(500));
                lock.lock();
                while (count.get() == EMPTY){
                    //消费者阻塞
                    empty.await();
                }
                System.out.println(Thread.currentThread().getName()
                        + " 消费者 消费完毕，count " + count.decrementAndGet());
                //唤醒生产者
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放锁
                lock.unlock();
            }
        }
    }
}
