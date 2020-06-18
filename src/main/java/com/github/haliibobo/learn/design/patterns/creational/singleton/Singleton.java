package com.github.haliibobo.learn.design.patterns.creational.singleton;

import com.github.haliibobo.learn.java.collection.GsonUtil;
import java.io.Serializable;
import java.util.Random;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-17 23:27
 * @description describe what this class do
 */
public class Singleton implements Serializable {

    private static volatile Singleton singleton;
    private int i;

    private Singleton(int i){
        this.i =i;
    }

    protected Object readResolve()
    {
        return singleton;
    }

    public static Singleton getSingleton(int i) {
        if(singleton == null){
           synchronized (Singleton.class){
                if(singleton == null) {
                    try {
                        Thread.sleep(new Random().nextInt(6));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    singleton = new Singleton(i);
                }
           }
       }
        System.out.println(singleton.i);
        return singleton;
    }

    public static void main(String[] args) {

        Singleton singleton1 = Singleton.getSingleton(2);
        Singleton singleton2 = Singleton.getSingleton(3);
        String s = GsonUtil.toJson(singleton1,false);

        Singleton singleton = GsonUtil.fromJson(s,Singleton.class);
        System.out.println(singleton1 == singleton);
        System.out.println(singleton1 == singleton2);
        /*for (int j = 0; j <20 ; j++) {
            new Thread(()->{
                for (int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(new Random().nextInt(5));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Singleton.getSingleton(new Random().nextInt(20));
                }
            }).start();
        }*/
    }
}
