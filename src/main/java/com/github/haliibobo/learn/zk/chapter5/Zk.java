package com.github.haliibobo.learn.zk.chapter5;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class Zk {
    private CountDownLatch downLatch = new CountDownLatch(1);
    private CountDownLatch downLatch2 = new CountDownLatch(1);
    private ZooKeeper zooKeeper;
    @Before
    public void setUp() throws Exception {
        zooKeeper =new ZooKeeper("halibobo.cn:2181", 5000, null);
    }

    @Test
     public void createZk() throws IOException, InterruptedException {
        ZooKeeper z = new ZooKeeper("halibobo.cn:2181", 5000, watchedEvent -> {
            System.out.println(watchedEvent.getState());
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                System.out.println(watchedEvent);
                downLatch.countDown();
            }
        });
        downLatch.await();
    }

    @Test
    public void createZk2() throws IOException, InterruptedException {
        ZooKeeper z = new ZooKeeper("halibobo.cn:2181", 5000, watchedEvent -> {
            System.out.println(watchedEvent.getState());
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                System.out.println(watchedEvent);
                downLatch.countDown();
            }
        });
        downLatch.await();
        long id =z.getSessionId();
        byte[] pwd = z.getSessionPasswd();

        //z.close();

        ZooKeeper z1 = new ZooKeeper("halibobo.cn:2181",5000,watchedEvent ->{
            System.out.println(watchedEvent.getState());
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                System.out.println(watchedEvent);
                downLatch2.countDown();
            }
            if (Watcher.Event.KeeperState.Expired == watchedEvent.getState()) {
                System.out.println(watchedEvent);
                downLatch2.countDown();
            }
        },id,pwd);
        downLatch2.await();
    }

    @Test
    public void createData() throws KeeperException, InterruptedException {
        zooKeeper.create("/halibobo","hello zk".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Assert.assertEquals("hello zk", new String(zooKeeper.getData("/halibobo",true,new Stat())));
    }
}
