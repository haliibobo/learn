package com.github.haliibobo.learn.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZkPathsUtil {
    private  String zkStr;
    private RetryPolicy retryPolicy;
    private CuratorFramework client;
    private String path;
    private ExecutorService tp = Executors.newFixedThreadPool(4);

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator/zkPaths";
        retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(50000)
                .connectionTimeoutMs(30000).retryPolicy(retryPolicy).build();
        client.start();
        client.blockUntilConnected();
    }

    @Test
    public void test() throws Exception {
        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
        System.out.println( ZKPaths.fixForNamespace(path,"/fixForNamespace"));
        System.out.println( ZKPaths.makePath(path,"/fixForNamespace"));
        System.out.println(ZKPaths.getNodeFromPath(path + "/fixForNamespace"));
        ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(path + "/fixForNamespace");
        System.out.println(pathAndNode.getNode());
        System.out.println(pathAndNode.getPath());
        String dir1 = path + "/child1";
        String dir2 = path + "/child2";
        ZKPaths.mkdirs(zooKeeper,dir1);
        ZKPaths.mkdirs(zooKeeper,dir2);
        System.out.println(ZKPaths.getSortedChildren(zooKeeper,path));
        ZKPaths.deleteChildren(zooKeeper,path,true);
    }
}
