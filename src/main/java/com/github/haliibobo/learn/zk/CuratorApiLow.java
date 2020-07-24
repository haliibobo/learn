package com.github.haliibobo.learn.zk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CuratorApiLow {

    private  String zkStr;
    private CuratorFramework curatorFramework;
    private String path;

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator";
        curatorFramework = CuratorFrameworkFactory.newClient(zkStr,
            10000, 15000,
            new RetryNTimes(10, 2000));
        curatorFramework.start();
    }


    /**
     * 当节点被删除后，就无法触发 nodeCacheListener了
     * @throws Exception
     */
    @Test
    public void childCacheListener() throws Exception {
        CountDownLatch c = new CountDownLatch(1);
        String path2 = path +"/del";
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,path2,false);
        pathChildrenCache.getListenable().addListener(this::onSync);
        pathChildrenCache.start(StartMode.POST_INITIALIZED_EVENT);
        c.await();
    }


    protected void onSync(CuratorFramework client, PathChildrenCacheEvent event) {
        switch (event.getType()) {
            case CHILD_ADDED:
                addChild(event.getData());
                break;
            case CHILD_REMOVED:
                removeChild(event.getData());
                break;
            case CHILD_UPDATED:
                updateChild(event.getData());
                break;
            default:
                break;
        }
    }

    private void addChild(ChildData data) {
        try {
            final String key = getLastPartOfPath(data.getPath());
            System.out.println("addChild:" +key);
            /*final String content = new String(data.getData());
            System.out.println("addChild:" +key +"," + content);*/
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void removeChild(ChildData data) {
        final String key = getLastPartOfPath(data.getPath());
        System.out.println("remove:" + key);
    }

    private void updateChild(ChildData data) {
        System.out.println("updateChild:" + data.getPath());
        addChild(data);
    }

    private String getLastPartOfPath(String path) {
        int index = path.lastIndexOf('/');
        if (index >= 0) {
            return path.substring(index + 1);
        } else {
            return path;
        }
    }

}
