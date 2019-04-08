package com.lunzi.camry.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * Created by lunzi on 2019/4/8 9:26 AM
 */
public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
    @SuppressWarnings("uncheck")
    private static void testBarrier(){
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"到达");
            try {
                //第一个线程
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"处理");
        });

        Thread t2=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"到达");
            try {
                //第二个线程
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"处理");
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        CyclicBarrierTest.testBarrier();
    }
}
