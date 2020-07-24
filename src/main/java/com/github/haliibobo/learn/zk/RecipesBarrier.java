package com.github.haliibobo.learn.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 分布式多线程同步
 * junit不支持多线程，主线程退出后子线程也退出 所以需要sleep
 */
public class RecipesBarrier {
    private final static String zkStr ="halibobo.cn:2181";
    private final static RetryPolicy retryPolicy =new ExponentialBackoffRetry(1000,3);
    private final static String path ="/halibobo/curator/barrier";
    private final static ExecutorService tp = Executors.newFixedThreadPool(4);


    @Test
    public void test() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(()->{
            try {
                Thread.sleep(1000);
                System.out.println("1号选手准备好了");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("1号选手起跑");
        });
        executor.submit(()->{
            try {
                Thread.sleep(2000);
                System.out.println("2号选手准备好了");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("2号选手起跑");
        });
        executor.submit(()->{
            try {
                Thread.sleep(3000);
                System.out.println("3号选手准备好了");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("3号选手起跑");
        });
        executor.shutdown();
        Thread.sleep(10000);
    }

    @Test
    public void recipesBarrier() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i= 0; i< 5;i++){
            executor.submit(() -> {
                try {
                    CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(5000)
                            .connectionTimeoutMs(3000).retryPolicy(retryPolicy).build();
                    client.start();
                    client.blockUntilConnected();
                    DistributedBarrier distributedBarrier = new DistributedBarrier(client, path);
                    distributedBarrier.setBarrier();
                    System.out.println(Thread.currentThread().getName() + " 号 set Barrier  ");
                    distributedBarrier.waitOnBarrier();
                    System.out.println(Thread.currentThread().getName() + "号 启动 ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(5000);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(5000)
                .connectionTimeoutMs(3000).retryPolicy(retryPolicy).build();
        client.start();
        client.blockUntilConnected();
        DistributedBarrier distributedBarrier = new DistributedBarrier(client, path);
        distributedBarrier.removeBarrier();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName());
        executor.shutdown();
    }

    @Test
    public void recipesBarrierLikeJDK() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i= 0; i< 5;i++){
            executor.submit(() -> {
                try {
                    CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(5000)
                            .connectionTimeoutMs(3000).retryPolicy(retryPolicy).build();
                    client.start();
                    client.blockUntilConnected();
                    DistributedDoubleBarrier distributedBarrier = new DistributedDoubleBarrier(client, path,5);
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + " 号 进入 Barrier  ");
                    distributedBarrier.enter();
                    System.out.println(Thread.currentThread().getName() + "号 启动 ");
                    Thread.sleep(new Random().nextInt(1000));
                    distributedBarrier.leave();
                    System.out.println(Thread.currentThread().getName() + "号 退出 ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName());
        executor.shutdown();
    }
}
