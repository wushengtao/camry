package com.lunzi.camry.threadpool;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者线程
 * Created by lunzi on 2018/10/19 上午11:23
 */
public class ProductorThread implements Runnable{
    private BlockingQueue<String> blockingQueue;
    public ProductorThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    @Override
    public void run() {
        try {
            while(true){
                String str=Thread.currentThread().getName()+"product"+"-test";
                blockingQueue.put(str);
                System.out.println(str);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
