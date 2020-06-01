package com.github.haliibobo.learn.zk.chapter5;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CuratorApi {

    private  String zkStr;
    private  RetryPolicy retryPolicy;
    private CuratorFramework curatorFramework;
    private String path;

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator";
        retryPolicy = new ExponentialBackoffRetry(100,3);
        curatorFramework = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(5000)
                .connectionTimeoutMs(3000).retryPolicy(retryPolicy).build();
        curatorFramework.start();
        curatorFramework.blockUntilConnected();
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
    public void createNodeFluent () throws Exception {
        curatorFramework.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
    }
    @Test
    public void getDataFluent () throws Exception {
        curatorFramework.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        Stat stat = new Stat();
        String data = new String(curatorFramework.getData().storingStatIn(stat).forPath(path));
        Assert.assertEquals("init",data);
    }
    @Test
    public void getChildrenFluent () throws Exception {
        Stat stat = new Stat();
        curatorFramework.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        System.out.println(curatorFramework.getChildren().storingStatIn(stat).forPath("/halibobo"));
    }
    @Test
    public void setDataFluent () throws Exception {
        curatorFramework.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        Stat stat = new Stat();
        String data = new String(curatorFramework.getData().storingStatIn(stat).forPath(path));
        Assert.assertEquals("init",data);
        curatorFramework.setData().withVersion(stat.getVersion()).forPath(path,"update".getBytes());
        String newData = new String(curatorFramework.getData().storingStatIn(stat).forPath(path));
        try {
            curatorFramework.setData().withVersion(stat.getVersion()).forPath(path);
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        Assert.assertEquals("update",newData);
    }

    @Test
    public void deleteNodeFluent () throws Exception {
        curatorFramework.create().orSetData().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        Stat stat = new Stat();
        String data = new String(curatorFramework.getData().storingStatIn(stat).forPath(path));
        Assert.assertEquals("init",data);
        stat =curatorFramework.setData().withVersion(stat.getVersion()).forPath(path,"update".getBytes());
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
        System.out.print(curatorFramework.checkExists().forPath(path));
    }
}
