package com.github.haliibobo.learn.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 允许多实例并发
 * https://stackoverflow.com/questions/423627/running-junit-tests-in-parallel-in-a-maven-build
 * IDEA-not allow to run in parallel
 */
public class MasterSelector {

    private  String zkStr;
    private RetryPolicy retryPolicy;
    private CuratorFramework client;
    private String path;
    private ExecutorService tp = Executors.newFixedThreadPool(4);

    @Before
    public void setUp() throws InterruptedException {
        zkStr = "halibobo.cn:2181";
        path = "/halibobo/curator";
        retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder().connectString(zkStr).sessionTimeoutMs(50000)
                .connectionTimeoutMs(30000).retryPolicy(retryPolicy).build();
        client.start();
        client.blockUntilConnected();
    }
    @Test
    public void test() throws Exception {
        PathChildrenCache cache = new PathChildrenCache(client,path,true);
        cache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        cache.getListenable().addListener((c,e)->{
            System.out.println("e.getType():"+e.getType());
            switch (e.getType()){
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED:" + e.getData().getPath() +"," +new String(e.getData().getData()));
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED:" + e.getData().getPath() +"," +new String(e.getData().getData()));
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED:" + e.getData().getPath());
                    break;
                default:System.out.println("default:" + e.getData().getPath());
                    break;
            }
        });
        LeaderSelector selector = new LeaderSelector(client, path, /*tp,*/ new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println(Thread.currentThread().getName());
                System.out.print("成为master角色");
                System.out.print("the job done");
                System.out.print("完成master 操作，释放master 权利！");
            }
        });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
