package com.lunzi.camry.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by lunzi on 2018/9/11 下午10:49
 */
public class TestZk {
    public static void main(String args[]) throws Exception {
        Objects.equals(null,"123");
//        CuratorFramework client=getClient();
//        client.start();
//        client.create().creatingParentsIfNeeded()
//                .withMode(CreateMode.EPHEMERAL)
//                .forPath("/distribute","lock".getBytes());
//        Thread.sleep(10000);
//        client.close();

    }
    public static CuratorFramework getClient(){
        String address="localhost:2181";
        RetryPolicy retryPolicy= new ExponentialBackoffRetry(1000,3);
        CuratorFramework client=CuratorFrameworkFactory.newClient(address,retryPolicy);
        return client;
    }






}
