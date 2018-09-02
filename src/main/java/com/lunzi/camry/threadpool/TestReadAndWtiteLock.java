package com.lunzi.camry.threadpool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lunzi on 2018/8/17 下午9:39
 */
public class TestReadAndWtiteLock {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private static CountDownLatch countDownLatch=new CountDownLatch(20);
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }
    public void handWrite(Lock lock,int index) throws InterruptedException {
        try{
            lock.lock();
            Thread.sleep(1000);
            value=index;
        }finally {
            lock.unlock();
        }
    }
    public static void main(String agrsp[]) throws InterruptedException {
        final TestReadAndWtiteLock test=new TestReadAndWtiteLock();
        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                try {
                    test.handleRead(TestReadAndWtiteLock.lock);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable run=new Runnable() {
            @Override
            public void run() {
                try {
                    test.handWrite(TestReadAndWtiteLock.lock,new Random().nextInt());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Long start=System.currentTimeMillis();
        System.out.println("start---"+start);
        for(int i=0;i<18;i++){
            Thread thread=new Thread(runnable);
            thread.start();
        }
        for(int i=18;i<20;i++){
            Thread thread=new Thread(run);
            thread.start();
        }
        countDownLatch.await();
        Long end=System.currentTimeMillis();
        System.out.println("end---"+end);
        System.out.println("all time is"+String.valueOf(end-start));
    }
}
