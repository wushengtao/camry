package com.lunzi.camry.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2018/7/21 下午3:08
 */
public class TestCountDownLatch {
    static CountDownLatch countDownLatch=new CountDownLatch(2);
    public static void main(String args[]) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is thread-1"+Thread.currentThread().getName());
                try {
                    System.out.println("线程1在执行任务");
                    System.out.println("线程1执行任务完毕");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("this is thread-2"+Thread.currentThread().getName());
                try {
                    System.out.println("线程2在执行任务");
                    Thread.sleep(5000);
                    System.out.println("线程2执行任务完毕");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("等待子线程执行");
        countDownLatch.await();
        System.out.println("子线程执行完毕");


    }
}
