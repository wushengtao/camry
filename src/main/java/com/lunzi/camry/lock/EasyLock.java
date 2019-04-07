package com.lunzi.camry.lock;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自己实现一个锁
 * Created by lunzi on 2019/4/7 11:13 AM
 */
public class EasyLock {
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

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    }
}