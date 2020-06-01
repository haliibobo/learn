package com.github.haliibobo.learn.zk.chapter5;

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

/**
 * 权限控制模块
 * scheme:world、auth、digest、ip和super
 * auth:具体的权限信息
 */
public class ZkACL {
    private ZooKeeper zk1;
    private ZooKeeper zk2;
    private ZooKeeper zk3;
    @Before
    public void setUp() throws Exception {
        zk1 =new ZooKeeper("halibobo.cn:2181", 5000,null);
        zk1.addAuthInfo("digest","halibobo:pass".getBytes());
        zk2 =new ZooKeeper("halibobo.cn:2181", 5000,null);
        zk2.addAuthInfo("digest","halibobo:pass".getBytes());
        zk3 =new ZooKeeper("halibobo.cn:2181", 5000,null);
        zk3.addAuthInfo("digest","halibobo:fail".getBytes());
    }

    //同步创建节点
    @Test
    public void getDataAcl() throws KeeperException, InterruptedException, IOException {
        String path = "/halibobo/tmp";
        zk1.create(path,"hello zk".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        System.out.print(new String(zk2.getData(path,false,null)));
        Assert.assertEquals(new String(zk1.getData(path,false,null)),new String(zk2.getData(path,false,null)));
    }

    /**
     * delete 删除权限仅限于对于子节点有效，如果一个没有子节点的节点被赋予了访问控制，删除控制无效的，依旧可以删除
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void deleteNodeAcl() throws InterruptedException, KeeperException {
        String path = "/halibobo/tmp";
        try {
            zk1.create(path,"hello zk".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            zk1.create(path+"/c1","".getBytes(),ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // 无权限情况，删除子节点
        try {
            zk3.delete(path+"/c1",-1);
        }catch (Exception e){
            //NoAuth
            System.out.println(e.getMessage());
        }
        // 删除有子节点的节点
        try {
            // Directory not empty
            zk2.delete(path,-1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // 有权限情况，删除子节点
        try {
           zk2.delete(path+"/c1",-1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // 无权限情况，删除无子节点的节点
        try {
            zk3.delete(path,-1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }







    }



}
