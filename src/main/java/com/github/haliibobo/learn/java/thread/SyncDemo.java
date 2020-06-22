package com.github.haliibobo.learn.java.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class SyncDemo {
    private static int count =0;
    private static  volatile int count2 =0;
    public static void main(String[] args) throws InterruptedException {
        /*synchronized (SyncDemo.class){

        }*/

        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    SyncDemo.add();
                }
            }).start();
        }
        Thread.sleep(10000);
        System.out.println(SyncDemo.count);
    }
    private static void  add (){
        count++;
    }
    private synchronized static void  add2 (){
        count2++;
    }
    private static void  addAll (){
        add();
        add2();
    }


    @Test
    public void test(){
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            new Thread(SyncDemo::addAll).start();
            //es.submit(SyncDemo::add2);
        }
        ReentrantLock reentrantLock;
        System.out.println("count: " + SyncDemo.count);
        System.out.println("count2: " + SyncDemo.count2);
    }
}
