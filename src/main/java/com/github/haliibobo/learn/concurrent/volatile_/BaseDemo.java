package com.github.haliibobo.learn.concurrent.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-31 15:36
 * @description describe what this class do
 */
public class BaseDemo {

    private final static int max =10;
    private static int init_value = 0;


    public static void main(String[] args) {
        new Thread(()->{
            int init_local = init_value;
            while (init_value < max){
                if (init_value != init_local){
                    System.out.printf("The init_value is update ot [%d]\n", init_value);
                    //对localValue进行重新赋值
                    init_local = init_value;
                }
            }

        },"reader").start();
        new Thread(()->{
            int localValue = init_value;
            while (localValue < max) {
                //修改init_value
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"updater").start();
    }



}
