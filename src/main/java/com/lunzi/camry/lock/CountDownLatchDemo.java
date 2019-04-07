package com.lunzi.camry.lock;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2019/4/7 10:18 AM
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            System.out.println("this is thread 1");
            try {
                //sleep 5 seconds
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            System.out.println("this is thread 2");
            try {
                //sleep 5 seconds
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        Thread t3 = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is thread t3");
        });
        Thread t4 = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is thread t4");
        });
        Thread t5 = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is thread t5");
        });



        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }


}
