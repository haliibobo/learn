package com.github.haliibobo.learn.zk.chapter5;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorApi {

    private  String zkStr;
    private  RetryPolicy retryPolicy;
    private CuratorFramework client;
    private String path;
    private CountDownLatch semaphore;
    private ExecutorService tp = Executors.newFixedThreadPool(1);

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator";
        semaphore = new CountDownLatch(2);
        retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(50000)
                .connectionTimeoutMs(30000).retryPolicy(retryPolicy).build();
        client.start();
        client.blockUntilConnected();
    }

    @Test
    public void createZk () throws InterruptedException {

        CuratorFramework client = CuratorFrameworkFactory.newClient(zkStr,
                5000,3000,retryPolicy);
        client.start();
        client.blockUntilConnected();
    }

    @Test
    public void createZkFluent () throws InterruptedException {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkStr).sessionTimeoutMs(5000).connectionTimeoutMs(3000).retryPolicy(retryPolicy)
                .namespace("/halibobo").build();
        client.start();
        client.blockUntilConnected();
    }

    @Test
    public void createNode () throws Exception {
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
    }
    @Test
    public void createNodeAsync () throws Exception {
        System.out.print("main thread: " +Thread.currentThread().getName());
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).
                inBackground(((client, event) -> {
                    System.out.println("event[code:"+event.getResultCode()+",type:" +event.getType()+"]");
                    System.out.println("Thread of processResult:" +Thread.currentThread().getName());
                    semaphore.countDown();
                }),tp).forPath(path,"init".getBytes());
        client.create()/*.orSetData()*/.creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).
                inBackground(((client, event) -> {
                    System.out.println("event[code:"+event.getResultCode()+",type:" +event.getType()+"]");
                    System.out.println("Thread of processResult:" +Thread.currentThread().getName());
                    semaphore.countDown();
                })).forPath(path,"init".getBytes());
        semaphore.await();
        tp.shutdown();
    }
    @Test
    public void getDataFluent () throws Exception {
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        Stat stat = new Stat();
        String data = new String(client.getData().storingStatIn(stat).forPath(path));
        Assert.assertEquals("init",data);
    }
    @Test
    public void getChildrenFluent () throws Exception {
        Stat stat = new Stat();
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        System.out.println(client.getChildren().storingStatIn(stat).forPath("/halibobo"));
    }
    @Test
    public void setDataFluent () throws Exception {
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        Stat stat = new Stat();
        String data = new String(client.getData().storingStatIn(stat).forPath(path));
        Assert.assertEquals("init",data);
        client.setData().withVersion(stat.getVersion()).forPath(path,"update".getBytes());
        String newData = new String(client.getData().storingStatIn(stat).forPath(path));
        try {
            client.setData().withVersion(stat.getVersion()).forPath(path);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        Assert.assertEquals("update",newData);
    }

    @Test
    public void deleteNodeFluent () throws Exception {
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        Stat stat = new Stat();
        String data = new String(client.getData().storingStatIn(stat).forPath(path));
        Assert.assertEquals("init",data);
        stat = client.setData().withVersion(stat.getVersion()).forPath(path,"update".getBytes());
        client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
        System.out.print(client.checkExists().forPath(path));
    }

    /**
     * 当节点被删除后，就无法触发 nodeCacheListener了
     * @throws Exception
     */
    @Test
    public void nodeCacheListener() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        client.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        final NodeCache nodeCache = new NodeCache(client,path,false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(()-> {
                    System.out.print("Node data update,new data:" +
                            new String(nodeCache.getCurrentData().getData()));
            countDownLatch.countDown();
                }
        );
        client.setData().forPath(path,"update".getBytes());
        countDownLatch.await();
        client.delete().deletingChildrenIfNeeded().forPath(path);
        //countDownLatch.await();
    }

    @Test
    public void pathChildrenCacheListener() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        PathChildrenCache cache = new PathChildrenCache(client,"/halibobo",true);
        cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        cache.getListenable().addListener((c,e)->{
            System.out.println("e.getType():"+e.getType());
            switch (e.getType()){
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED:" + e.getData().getPath() +"," +new String(e.getData().getData()));
                    countDownLatch.countDown();
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED:" + e.getData().getPath() +"," +new String(e.getData().getData()));
                    countDownLatch.countDown();
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED:" + e.getData().getPath());
                    countDownLatch.countDown();
                    break;
                default:System.out.println("default:" + e.getData().getPath());
                    break;
            }
        },tp);
        client.create().withMode(CreateMode.PERSISTENT).forPath("/halibobo/zk","init".getBytes());
        //countDownLatch.await();
        client.setData().forPath("/halibobo/zk","good".getBytes());
        client.setData().forPath("/halibobo/zk","good2".getBytes());
        client.setData().forPath("/halibobo/zk","good3".getBytes());
        client.setData().forPath("/halibobo/zk","good4".getBytes());
        client.setData().forPath("/halibobo/zk","good5".getBytes());
        //countDownLatch.await();
       //client.setData().forPath("/halibobo","halibobo".getBytes());
        client.delete().deletingChildrenIfNeeded().forPath("/halibobo/zk");
        countDownLatch.await();
        tp.shutdown();
    }
}
