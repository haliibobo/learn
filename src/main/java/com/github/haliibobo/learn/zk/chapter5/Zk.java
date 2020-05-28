package com.github.haliibobo.learn.zk.chapter5;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Zk {
    private CountDownLatch downLatch = new CountDownLatch(1);
    private CountDownLatch downLatch2 = new CountDownLatch(1);
    private CountDownLatch downLatch3 = new CountDownLatch(1);
    private ZooKeeper zooKeeper;
    @Before
    public void setUp() throws Exception {
        zooKeeper =new ZooKeeper("halibobo.cn:2181", 5000, watchedEvent ->{
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                downLatch3.countDown();
            }
        });
        downLatch3.await();
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
//复用session
    @Test
    public void createZk2() throws IOException, InterruptedException {
        ZooKeeper z = new ZooKeeper("halibobo.cn:2181", 5000, watchedEvent -> {
            System.out.println(watchedEvent.getState());
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
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

    //同步创建节点
    @Test
    public void createDataSync() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        String path =zooKeeper.create("/halibobo/tmp","hello zk".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,stat);
        String path2 =zooKeeper.create("/halibobo/tmp","hello zk".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(path);
        System.out.println(stat);
        System.out.println(path2);
        Assert.assertEquals("hello zk", new String(zooKeeper.getData("/halibobo/tmp",
                true,new Stat())));
    }
    //异步创建节点
    @Test
    public void createDataAsync() throws InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Map<String,Object> result =  new HashMap<>();
        zooKeeper.create("/halibobo/tmp","hello zk async".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, (rc, path, ctx, name) -> {
                    ((Map)ctx).put("rc",rc);
                    ((Map)ctx).put("path",path);
                    ((Map)ctx).put("name",name);
                    countDownLatch.countDown();
                },result);
        countDownLatch.await();
        System.out.print(result);
            Assert.assertEquals("hello zk async", new String(zooKeeper.getData((String) result.get("name"),
                    true,new Stat())));
    }


    //同步获取节点数据
    @Test
    public void getDataSync() throws InterruptedException, KeeperException {
        Map<String,String> m= new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(1);
        String path =zooKeeper.create("/halibobo/tmp","hello zk async".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        String value = new String(zooKeeper.getData(path,watcherEvent->{
            if(Watcher.Event.EventType.NodeDataChanged == watcherEvent.getType()){
                try {
                    m.put("v",new String(zooKeeper.getData(watcherEvent.getPath(),true,new Stat())));
                    latch.countDown();
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },new Stat()));
        m.put("v",value);
        System.out.println(m);
        zooKeeper.setData(path,"hello zk async changed".getBytes(StandardCharsets.UTF_8),-1);
        System.out.println(m);
        latch.await();
        System.out.println(m);
        Assert.assertEquals("hello zk async changed", m.get("v"));
    }

    //异步获取节点数据
    @Test
    public void getDataAsync() throws InterruptedException, KeeperException {
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);
        CountDownLatch latch4 = new CountDownLatch(1);
        Map<String,String> m= new ConcurrentHashMap<>();
        zooKeeper.create("/halibobo/tmp","hello zk async".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, (rc, path, ctx, name) -> {
                    m.put("p",name);
                    latch.countDown();
                },m);
        latch.await();
        zooKeeper.getData(m.get("p"),watcherEvent->{
            if(Watcher.Event.EventType.NodeDataChanged == watcherEvent.getType()){
                try {
                    m.put("v",new String(zooKeeper.getData(watcherEvent.getPath(),true,new Stat())));
                    latch4.countDown();
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, (rc, path, ctx, data, stat) -> {
            ((Map)ctx).put("v",new String(data));
            latch2.countDown();
        },m);
        latch2.await();
        zooKeeper.setData(m.get("p"),"hello zk async changed".getBytes(StandardCharsets.UTF_8),-1, (rc, path, ctx, stat) -> {
            latch3.countDown();
        },m);
        latch3.await();
        System.out.print(m);
        latch4.await();
        Assert.assertEquals("hello zk async changed", m.get("v"));
    }
}
