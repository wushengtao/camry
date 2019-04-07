package com.lunzi.camry.lock;

import java.util.concurrent.Semaphore;

/**
 * 测试信号量
 * Created by lunzi on 2019/4/7 9:14 PM
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore=new Semaphore(3);
        //第一个线程获得许可
        Thread thread=new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"获得许可");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        });
        thread.start();

        for(int i=0;i<5;i++){
            Thread t=new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"获得许可");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
            t.start();
        }
    }
}
