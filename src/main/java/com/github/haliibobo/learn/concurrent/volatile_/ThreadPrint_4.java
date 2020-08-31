package com.github.haliibobo.learn.concurrent.volatile_;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-31 17:17
 * @description describe what this class do
 */
public class ThreadPrint_4 {

    private static  C c = new C();

    static class C {

        public volatile char x = 'A';
    }

    public static void main(String[] args) {
        new T(c, 'A', 'B').start();
        new T(c, 'B', 'C').start();
        new T(c, 'C', 'A').start();
    }


    static class T extends Thread {
        public T(C c, char sysChar, char nextChar) {
            this.c = c;
            this.sysChar = sysChar;
            this.nextChar = nextChar;
        }

        public  C c;
        public char sysChar;
        public char nextChar;

        @Override
        public void run() {
            while (true) {
                if (c.x == sysChar) {
                    System.out.println(sysChar);
                    c.x = nextChar;
                    //System.out.println(b);
                } else {
                    //System.out.println("ccccccc===="+c.x);
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}