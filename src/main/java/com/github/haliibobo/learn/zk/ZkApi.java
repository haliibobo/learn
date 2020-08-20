package com.github.haliibobo.learn.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ZkApi {
    private CountDownLatch downLatch = new CountDownLatch(1);
    private CountDownLatch downLatch2 = new CountDownLatch(1);
    private CountDownLatch downLatch3 = new CountDownLatch(1);
    private CountDownLatch downLatch4 = new CountDownLatch(7);
    private ZooKeeper zooKeeper;
    @Before
    public void setUp() throws Exception {
        zooKeeper =new ZooKeeper("halibobo.cn:2181", 5000, watchedEvent ->{
            if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                if(Watcher.Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                    downLatch3.countDown();
                }
                try {
                    switch (watchedEvent.getType()){
                        case NodeChildrenChanged: {
                            System.out.print("reGetChildren:" +
                                    zooKeeper.getChildren(watchedEvent.getPath(),true));
                            downLatch4.countDown();
                            break;
                        }
                        case NodeCreated: {
                            System.out.println("Node("+ watchedEvent.getPath() +") created");
                            //监听事件通知是一次性的，一旦触发，该监听器失效，因此需要反复注册监听器
                            zooKeeper.exists(watchedEvent.getPath(),true);
                            downLatch4.countDown();
                            break;
                        }
                        case NodeDeleted: {
                            System.out.println("Node("+ watchedEvent.getPath() +") deleted");
                            zooKeeper.exists(watchedEvent.getPath(),true);
                            downLatch4.countDown();
                            break;
                        }
                        case NodeDataChanged: {
                            System.out.println("Node("+ watchedEvent.getPath() +") dataChanged");
                            zooKeeper.exists(watchedEvent.getPath(),true);
                            downLatch4.countDown();
                            break;
                        }
                    }
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
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
        //zooKeeper.removeWatches();
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
        zooKeeper.setData(m.get("p"),"hello zk async changed".getBytes(StandardCharsets.UTF_8),-1,
                (rc, path, ctx, stat) -> latch3.countDown()
        ,m);
        latch3.await();
        System.out.print(m);
        latch4.await();
        Assert.assertEquals("hello zk async changed", m.get("v"));
    }

    //同步获取叶子节点数据
    @Test
    public void getChildrenSync() throws InterruptedException, KeeperException {
        List<String> children = zooKeeper.getChildren("/halibobo",true);
        System.out.println("getChildren" + children);
        String ss =zooKeeper.create("/halibobo/tmp","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println(ss);
        downLatch4.await();
    }

    //异步获取叶子节点数据
    @Test
    public void getChildrenAsync() throws InterruptedException, KeeperException {
        CountDownLatch tmp = new CountDownLatch(1);
        zooKeeper.getChildren("/halibobo",true,(rc,path, ctx, children,  stat) ->{
            System.out.println("rc: " + rc);
            System.out.println("path: " + path);
            System.out.println("ctx: " + ctx);
            System.out.println("children: " + children);
            System.out.println("stat: " + stat);
            tmp.countDown();
        },null);
        tmp.await();
        String ss =zooKeeper.create("/halibobo/tmp","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println(ss);
        downLatch4.await();
    }

    /**
     * 同步修改节点数据.
     * version cas原理 compare and swap
     * 期望值版本号version -1 基于最新版本更新，对更新操作没有原子性要求
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void setDataSync() throws InterruptedException, KeeperException {
        String path =zooKeeper.create("/halibobo/tmp","hello zk".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Stat stat= zooKeeper.setData(path,"hello zk changed".getBytes(StandardCharsets.UTF_8),-1);
        System.out.println(stat.getCzxid() +","+stat.getMzxid() +"," +stat.getVersion());
        Stat stat2 = zooKeeper.setData(path,"hello zk changed".getBytes(StandardCharsets.UTF_8),stat.getVersion());
        System.out.println(stat2.getCzxid() +","+stat2.getMzxid() +"," +stat2.getVersion());
        try {
            zooKeeper.setData(path,"hell zk".getBytes(StandardCharsets.UTF_8),stat.getVersion());
        }catch (KeeperException e){
            System.out.print(e.getMessage());
        }
    }


    /**
     * 异步修改节点数据.
     * version cas原理 compare and swap
     * 期望值版本号version -1 基于最新版本更新，对更新操作没有原子性要求
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void setDataAsync() throws InterruptedException, KeeperException {
        String path =zooKeeper.create("/halibobo/tmp","hello zk".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper.setData(path,"hello zk changed async".getBytes(StandardCharsets.UTF_8),-1,(rt,p,ctx,stat) ->{
              if(rt == 0){
                  try {
                      System.out.print("getNewData:" + new String(zooKeeper.getData(p,true,stat)));
                  } catch (KeeperException | InterruptedException e) {
                      e.printStackTrace();
                  }
                  countDownLatch.countDown();
              }
        },null);
        countDownLatch.await();
    }

    /**
     * 同步判断节点是否存在 通过exit 注册的监听事件只对 该节点创建、删除、更新 生效，不会对子节点生效
     */
    @Test
    public void exitNodeSync() throws InterruptedException, KeeperException {
        String path = "/halibobo/tmp";
        zooKeeper.exists(path,true);
        zooKeeper.create(path,"hello zk".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.setData(path,"hello zk changed async".getBytes(StandardCharsets.UTF_8),-1);
        zooKeeper.setData(path,"hello zk2 changed async".getBytes(StandardCharsets.UTF_8),-1);
        zooKeeper.setData(path,"hello zk3 changed async".getBytes(StandardCharsets.UTF_8),-1);
        zooKeeper.setData(path,"hello zk4 changed async".getBytes(StandardCharsets.UTF_8),-1);
        zooKeeper.setData(path,"hello zk5 changed async".getBytes(StandardCharsets.UTF_8),-1);
        zooKeeper.delete(path,-1);
        downLatch4.await();
    }



}
