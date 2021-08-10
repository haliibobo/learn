package com.github.haliibobo.learn.java.lock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * say something.
 *javap -c -s -v -l
 * @author lizibo
 * @version 1.0
 * @date 2020-06-24 15:11
 * @description describe what this class do
 */
public class SynchronizedLearn {
    private Object o = new Object();
    private int i;
    public synchronized void  test(){
        i++;
    }
    public  void  test2(){
        synchronized (this){
            i++;
        }
    }
    public  void  test3(){
        synchronized (o){
            i++;
        }
    }
    public static synchronized void  test4(){
    }
    public int  test5(){
        Map<String,SynchronizedLearn> m =  new HashMap<>();
        m.forEach((k,v)->{

        });
       // System.out.println(Arrays.toString());
        synchronized (SynchronizedLearn.class){
            i++;
        }
        return i;
    }
}