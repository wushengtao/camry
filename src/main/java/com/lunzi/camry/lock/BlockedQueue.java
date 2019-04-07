package com.lunzi.camry.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一把锁实现入队出队
 * Created by lunzi on 2019/4/7 9:36 AM
 */
public class BlockedQueue {
    //锁
    private ReentrantLock reentrantLock = new ReentrantLock();

    //条件
    private Condition notFull = reentrantLock.newCondition();//空
    private Condition notEmpty = reentrantLock.newCondition();//满

    //list作为一个队列
    private List<Object> list = new LinkedList<>();

    //入队操作
    private void push() {
        //先拿一把锁
        reentrantLock.lock();
        try {
            //入队操纵确保队列不是满的
            //队列满的就等待消耗
            while (list.size() == 2) {
                System.out.println("等待入队操作");
                notFull.await();
            }
            //入队操作
            Thread.sleep(100);
            list.add(new Object());
            System.out.println("进行了入队操作");
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //最后释放锁
            reentrantLock.unlock();
        }
    }

    public void pull() {
        reentrantLock.lock();
        try {
            while (list.size() == 0) {
                System.out.println("等待出队操作");
                notEmpty.await();
            }
            Thread.sleep(100);
            list.remove(0);
            System.out.println("进行了出队操作");
            notFull.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockedQueue blockedQueue = new BlockedQueue();
        //十个线程
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (finalI % 2 == 0) {
                        blockedQueue.pull();
                    } else {
                        blockedQueue.push();
                    }
                }
            });
            thread.start();
        }


    }

}
