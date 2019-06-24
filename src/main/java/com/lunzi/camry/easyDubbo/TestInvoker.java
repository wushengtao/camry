package com.lunzi.camry.easyDubbo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lunzi on 2019/5/21 10:24 PM
 */
public class
TestInvoker {
    private final static Lock lock = new ReentrantLock();
    private final static Condition condition = lock.newCondition();
    private static volatile Boolean isDone = false;

    public static void main(String[] args) {
        //模拟请求线程
        new Thread(() -> {
            String result = getResult();
            System.out.println(result);
        }).start();

        //模拟相应的线程 修改共享状态的值
        new Thread(() -> {
            System.out.println("模拟接收到请求,请求时间为3秒钟");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isDone = true;
        }).start();

    }

    private static String getResult() {
        //未完成进入执行的逻辑
        if (!isDone) {
            lock.lock();
            //另外一条线程来改变这个值
            while (!isDone) {
                //挂起 500毫秒
                try {
                    System.out.println("挂起了500毫秒");
                    condition.await(500, TimeUnit.MILLISECONDS);
                    //这边可以添加超时的逻辑
                    if (isDone) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
        return "success";
    }
}

