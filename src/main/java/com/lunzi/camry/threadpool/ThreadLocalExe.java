package com.lunzi.camry.threadpool;


import com.lunzi.camry.domain.User;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2019/3/12 11:42 AM
 */
public class ThreadLocalExe {
    public static void main(String[] args) {
        MyThread1 mt = new MyThread1();
        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        t1.start();
        t2.start();
    }
}
class MyThread1 implements Runnable {
    public int ticket = 10;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
            }
        }
    }
}
