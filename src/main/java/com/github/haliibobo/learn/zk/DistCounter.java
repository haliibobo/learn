package com.github.haliibobo.learn.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 分布式计数器
 */
public class DistCounter {
    private  String zkStr;
    private RetryPolicy retryPolicy;
    private CuratorFramework client;
    private String path;
    private ExecutorService tp = Executors.newFixedThreadPool(4);

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator/dist_count";
        retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(50000)
                .connectionTimeoutMs(30000).retryPolicy(retryPolicy).build();
        client.start();
        client.blockUntilConnected();
    }
    @Test
    public void test() throws Exception {

        client.delete().quietly().forPath(path);
        DistributedAtomicInteger atomicInteger =
                new DistributedAtomicInteger(client,path,new RetryForever(500));
        System.out.println("begin:" +atomicInteger.get().postValue());
        Stat stat = new Stat();
        final CountDownLatch down = new CountDownLatch(10);
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                try {
                   System.out.println(atomicInteger.add(2).succeeded());
                    down.countDown();
                } catch (Exception e) {
                }

            }).start();
        }
        down.await();
        System.out.println(Arrays.toString(ByteBuffer.allocate(4).putInt(60).array()));
        System.out.println(Arrays.toString(client.getData().storingStatIn(stat).forPath(path)));
        System.out.println(ByteBuffer.wrap(client.getData().storingStatIn(stat).forPath(path)));
        System.out.println(ByteBuffer.wrap(client.getData().storingStatIn(stat).forPath(path)).getInt());
        Assert.assertEquals(new Integer(20),atomicInteger.get().postValue());
    }
}
