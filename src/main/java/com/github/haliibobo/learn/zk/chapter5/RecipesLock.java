package com.github.haliibobo.learn.zk.chapter5;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipesLock {

    private  String zkStr;
    private RetryPolicy retryPolicy;
    private CuratorFramework client;
    private String path;
    private ExecutorService tp = Executors.newFixedThreadPool(4);

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator/lock";
        retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(50000)
                .connectionTimeoutMs(30000).retryPolicy(retryPolicy).build();
        client.start();
        client.blockUntilConnected();
    }

    @Test
    public void noLock() throws InterruptedException {
        final CountDownLatch  down = new CountDownLatch(10);
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                String ordNO = sdf.format(new Date());
                System.out.println("ordNo:" + ordNO);
                down.countDown();

            }).start();
        }
        down.await();
    }
    @Test
    public void recipesLock() throws InterruptedException {
        final CountDownLatch  down = new CountDownLatch(10);
        final InterProcessMutex mutex = new InterProcessMutex(client,path);
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                try {
                    mutex.acquire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                String ordNO = sdf.format(new Date());
                System.out.println("ordNo:" + ordNO);
                down.countDown();
                try {
                    mutex.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }
        down.await();
    }
}
