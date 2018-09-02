package com.lunzi.camry.threadpool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lunzi on 2018/7/23 下午11:47
 */
public class TestCondition {
    public static ReentrantLock reentrantLock=new ReentrantLock();
    public static Condition condition=reentrantLock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    System.out.println("执行");
                    condition.await();
                    System.out.println("continue to do something");
                    reentrantLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        reentrantLock.lock();
        System.out.println("main thread");
        //condition.signal();
        System.out.println("main end");
        reentrantLock.unlock();
    }


}
