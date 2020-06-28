package com.github.haliibobo.learn.java.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-29 16:19
 * @description describe what this class do
 */
public class DeadLock {


    private static final List<Integer>  lock= Collections.synchronizedList(new ArrayList<>());


    private static void  remove (Integer i){
        synchronized (lock){
            lock.remove(i);
        }
    }

    private static void  remove (int i){
        synchronized (lock){
            lock.remove(i);
        }
    }

    private static void  add (Integer i){
        synchronized (lock){
            lock.add(i);
        }
    }



    @Test
    public  void test () throws InterruptedException {
        for (int i = 0; i <100000 ; i++) {
            new Thread(()->{

                    DeadLock.add(new Random().nextInt(10));

            }).start();
        }
        for (int i = 0; i <100000 ; i++) {
            new Thread(()->{
                if(!DeadLock.lock.isEmpty()){
                    DeadLock.remove(0);

                }

            }).start();
        }





        while (Thread.activeCount()>2){
            Thread.yield();

        }
        System.out.println("end");
    }
}
