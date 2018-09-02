package com.lunzi.camry.threadpool;

import java.util.concurrent.*;

/**
 * Created by lunzi on 2018/8/12 下午10:23
 */
public class ArrayBlockQueueTest {
    public static void main(String args[]) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(5);
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Thread thread1=new Thread(()->{
            while(true){
                try {
                    //arrayBlockingQueue.put((Runnable) () -> System.out.println("producer one"));
                    System.out.println("test");
                    Thread.sleep(10000);
                    //Thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2=new Thread(()->{
            while(true){
                try {
                    executorService.execute((Runnable) arrayBlockingQueue.take());
                    System.out.println("consumer one");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();

    }
}
