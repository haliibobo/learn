package com.github.haliibobo.learn.concurrent.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-31 17:17
 * @description 只有直接访问 被volatile 修饰的对象或者属性才可以，重新定向被volatile 修饰的对象不行
 */
public class ThreadPrint {

    private static volatile C c = new C();

   static class C {
        public char x = 'A';
    }

    public static void main(String[] args) {


        new Thread(() -> {
            while(true) {
                if(c.x == 'A') {
                    System.out.println('A');
                    c.x = 'B';
                }else {
                    //System.out.println("aaaaaa===="+c.x);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while(true) {
                if(c.x == 'B') {
                    System.out.println('B');
                    c.x = 'C';
                }else {
                    //System.out.println("bbbbbbbb===="+c.x);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while(true) {
                if(c.x == 'C') {
                    System.out.println('C');
                    c.x = 'A';
                }else {
                    //System.out.println("ccccccc===="+c.x);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
