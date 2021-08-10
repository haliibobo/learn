package com.github.haliibobo.learn.thread;

import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-29 14:15
 * @description describe what this class do
 */
public class ThreadLocalLearn {



    @Test
    public void test(){
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        System.out.println(Thread.currentThread());

        Thread thread = new Thread(()->{
            threadLocal.set(5555);
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal.get());
        });
        thread.start();

        threadLocal.set(6666);
        System.out.println(threadLocal.get());
    }

}
