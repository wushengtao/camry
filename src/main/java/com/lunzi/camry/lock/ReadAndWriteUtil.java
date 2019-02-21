package com.lunzi.camry.lock;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁工具
 * Created by lunzi on 2019/2/20 11:29 AM
 */
public class ReadAndWriteUtil {
    //读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock=readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock=readWriteLock.writeLock();
    private static ReentrantLock reentrantLock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //创建线程池
        ExecutorService executorService= Executors.newFixedThreadPool(5);
        CountDownLatch downLatch=new CountDownLatch(10);
        Long start=System.currentTimeMillis();
        //循环创建五个线程
        for(int i=0;i<10;i++){
            if(i%2==0){
                executorService.submit(new CostTimeTask(writeLock,downLatch));
            }else {
                executorService.submit(new CostTimeTask(writeLock,downLatch));
            }
        }
        downLatch.await();
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    static class CostTimeTask implements Runnable{
        private Lock lock;
        private CountDownLatch countDownLatch;
        public CostTimeTask(Lock lock,CountDownLatch countDownLatch){
            this.lock=lock;
            this.countDownLatch=countDownLatch;
        }
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"执行");
                Thread.sleep(500);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
}
