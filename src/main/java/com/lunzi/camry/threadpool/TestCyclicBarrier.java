package com.lunzi.camry.threadpool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试线程屏障
 * Created by lunzi on 2018/7/20 上午8:44
 */
public class TestCyclicBarrier {
    public static final CyclicBarrier cyclicBarrier=new CyclicBarrier(10);
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+Thread.currentThread().getName()+"准别就绪");
                    try {
                        cyclicBarrier.await();
                        System.out.println("线程"+Thread.currentThread().getName()+"继续执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

}
