package com.github.haliibobo.learn.kuaishou;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumePush<T> {

    private int size;
    private int delay;
    private List<T> container;
    final ScheduledExecutorService es ;
    private final ReentrantLock lock = new ReentrantLock();


    public ProduceConsumePush(){
        this.size =8;
        this.delay = 1;
        container = Collections.synchronizedList(new ArrayList<>(size));
        es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleWithFixedDelay( new Consumer(),this.delay,this.delay, TimeUnit.SECONDS);
    }

    /*public ProduceConsumePush(int size, int delay){
        this.size =size;
        this.delay = delay;
        container = Collections.synchronizedList(new ArrayList<>(size));
        es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleWithFixedDelay( new Consumer(),this.delay,this.delay, TimeUnit.SECONDS);
    }*/



    public void add(T t){
        es.schedule(new Producer(t),0,TimeUnit.NANOSECONDS);
    }

    @Test
    public void test() {
        ProduceConsumePush<Integer> produceConsumePush  = new ProduceConsumePush<>();
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            produceConsumePush.add(new Random().nextInt(2000));
        }

    }


    class Producer implements Runnable{
        T t;
         Producer(T t){
            this.t=t;
        }

        @Override
        public void run() {
            try {
                //Thread.sleep(new Random().nextInt(500));
                lock.lock();
                container.add(t);

                /*System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()
                        + " 生产者 生产完毕， " + container);*/
                container.add(t);
                if (container.size() >= size){
                    System.out.println("buffer 达到上限，开始消费," + container.size());
                    es.schedule(new Consumer(),0,TimeUnit.NANOSECONDS);
                }

            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }
    class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                lock.lock();
                Thread.sleep(new Random().nextInt(500));
                container.clear();
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName()
                        + " 消费者 消费完毕, " + container);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
