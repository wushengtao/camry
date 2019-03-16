package com.lunzi.camry.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2019/3/16 12:01 PM
 */
public class TestThread {
    private volatile long count = 0;

    public synchronized long getCount() {
        return count;
    }

    public synchronized void setCount(long count) {
        this.count = count;
    }

    public void add() {
        int idx = 0;
        while (idx++ < 10000){
            setCount(getCount() + 1);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        TestThread thread=new TestThread();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                thread.add();
                countDownLatch.countDown();
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                thread.add();
                countDownLatch.countDown();
            }
        });
        thread1.start();
        thread2.start();
        countDownLatch.await();
        System.out.println(thread.getCount());

    }

}
