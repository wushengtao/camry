package com.lunzi.camry.easyThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by lunzi on 2019/5/23 10:08 AM
 */
public class EasyThreadPoolExecutor extends EasyAbstractExecutorService {
    private int corePoolSize;//核心线程
    private int maxiumPoolSize;//最大线程
    private long keepAlveTime;//存活时间
    private BlockingQueue blockingQueue;//工作队列
    private RejectedExecutionHandler rejectedExecutionHandler;

    private AtomicInteger workThread = new AtomicInteger(0);//工作线程计数

    EasyThreadPoolExecutor(int corePoolSize,
                           int maximumPoolSize,
                           long keepAliveTime,
                           TimeUnit unit,
                           BlockingQueue<Runnable> workQueue,
                           RejectedExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maxiumPoolSize = maximumPoolSize;
        this.keepAlveTime = keepAliveTime;
        this.blockingQueue = workQueue;
        this.rejectedExecutionHandler = handler;
    }

    public EasyThreadPoolExecutor(int threadNum) {
        this(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), null);
    }


    @Override
    public void execute(Runnable runnable) {
        //权限和线程池的状态校验 暂时没理清楚
        int workThreadSize = workThread.get();
        if (workThreadSize < corePoolSize) {
            //新建一个线程
        }
    }

    private final class EasyWorker
            extends AbstractQueuedSynchronizer
            implements Runnable {
        private Runnable runnable;

        protected EasyWorker() {

        }

        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
           setExclusiveOwnerThread(null);
           setState(0);
           return true;
        }


        @Override
        protected boolean isHeldExclusively() {
            return getState()!=0;
        }

        @Override
        public void run() {
        }

        public void lock() {
            acquire(1);
        }

        public boolean tryLock() {
            return tryAcquire(1);
        }

        public void unlock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }
    }
}
