package com.lunzi.camry.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lunzi on 2018/10/19 上午11:45
 */
public class ConsumerThread implements Runnable {
    private BlockingQueue<String>  blockingQueue;
    public ConsumerThread(BlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    @Override
    public void run() {
        try {
            while(true){
                String str=blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"consumer"+"-"+str);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        BlockingQueue<String> blockingQueue=new LinkedBlockingDeque<>(10);
        ProductorThread productorThread=new ProductorThread(blockingQueue);
        ProductorThread productorThread1=new ProductorThread(blockingQueue);
        ProductorThread productorThread2=new ProductorThread(blockingQueue);
        ConsumerThread consumerThread=new ConsumerThread(blockingQueue);
        ConsumerThread consumerThread1=new ConsumerThread(blockingQueue);
        ConsumerThread consumerThread2=new ConsumerThread(blockingQueue);
        ExecutorService es= Executors.newFixedThreadPool(10);
        es.execute(productorThread);
        es.execute(productorThread1);
        es.execute(productorThread2);
        es.execute(consumerThread);
        es.execute(consumerThread1);
        es.execute(consumerThread2);



    }
}
