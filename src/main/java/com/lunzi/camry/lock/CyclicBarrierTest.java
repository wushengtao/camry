package com.lunzi.camry.lock;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * 循环栅栏
 * Created by lunzi on 2019/4/8 9:26 AM
 */
public class CyclicBarrierTest {
    //队列1
    static BlockingQueue blockedQueue1 = new LinkedBlockingDeque(5);
    //队列2
    static BlockingQueue blockingQueue2 = new LinkedBlockingDeque(5);

    public static void test1() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("执行取对象的操作");
            blockedQueue1.remove();
            blockingQueue2.remove();
        });
        Thread thread1 = new Thread(() -> {
            //循环十次
            for (int i = 0; i < 10; i++) {
                System.out.println("队列1增加对象");
                blockedQueue1.add(new Object());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("队列2增加对象");
                blockingQueue2.add(new Object());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }

    public static void test2() {
        CompletableFuture<Object> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("队列1增加对象");
            return new Object();
        });
        CompletableFuture<Object> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("队列2增加对象");
            return new Object();
        });
        completableFuture1.thenCombine(completableFuture2,(o, o2) ->{
            System.out.println("组合");
            return true;
        }).thenAccept(o -> {
            System.out.println(o);
            System.out.println("保存");
        });
    }

    public static void main(String[] args) {
        test2();
    }

}
