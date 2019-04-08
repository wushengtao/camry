package com.lunzi.camry.lock;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自己实现一个锁
 * Created by lunzi on 2019/4/7 11:13 AM
 */
public class EasyLock {
    private final Sync sync = new Sync();
    public static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() {
            //是否持有锁
            return getState()==1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

    }
    public Boolean lock(){
        return sync.tryAcquire(1);
    }

    public Boolean unlock(){
        return sync.release(1);
    }

    public static void main(String[] args) {
        final ReentrantLock reentrantLock=new ReentrantLock();
        final EasyLock easyLock=new EasyLock();
        Thread t1=new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("1获得了锁");
                Thread.sleep(3000);
                System.out.println("1结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });

        Thread t2=new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("2获得了锁");
                Thread.sleep(3000);
                System.out.println("2结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        });
        t1.start();
        t2.start();
    }
}
