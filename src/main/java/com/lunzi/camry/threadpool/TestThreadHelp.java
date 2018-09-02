package com.lunzi.camry.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2018/8/12 下午9:58
 */
public class TestThreadHelp {
    public static CountDownLatch latch=new CountDownLatch(3);
    public static void main(String args[]) throws InterruptedException {
        for(int i=0;i<2;i++){
            new Thread(() -> {
                latch.countDown();
                System.out.println("this is test");
            }).start();
        }
        System.out.println("wait...");
        latch.await();
        System.out.println("end");
    }

}
