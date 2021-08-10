package com.github.haliibobo.learn.sys;

import com.github.haliibobo.learn.finagle.HttpServer;

public class Exit {

    public static void main(String[] args) throws Exception {
        Exit e = new Exit();
        HttpServer s = new HttpServer();
        Runtime.getRuntime().addShutdownHook(new Thread(e::shut));
        e.run();

    }
    public static final Object o = new Object();


    public synchronized void run () {
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ee) {
            ee.printStackTrace();
        }
        System.exit(0);
    }

    public void shut(){
        synchronized (o){
            System.out.println("shutdown");
        }
    }
}



