package com.lunzi.camry.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by lunzi on 2018/8/19 下午8:39
 */
public class TestLockSupport {
    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("int 1");
                //Thread.currentThread().suspend();
                LockSupport.park();
            }
        };
        Thread thread1=new Thread(runnable);
        Thread thread2=new Thread(runnable);
        thread1.start();
        thread2.start();
        LockSupport.unpark(thread1);
        LockSupport.unpark(thread2);
    }
}
