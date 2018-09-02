package com.lunzi.camry.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lunzi on 2018/8/10 上午9:00
 */
public class TestSync {
    private static ReentrantLock reentrantLock=new ReentrantLock();
    public static void print(){
        reentrantLock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("12312");
        reentrantLock.unlock();
    }
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    print();
                }
            });
            thread.start();
        }
    }

}
