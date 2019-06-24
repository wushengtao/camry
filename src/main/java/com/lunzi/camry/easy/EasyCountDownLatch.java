package com.lunzi.camry.easy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by lunzi on 2019/6/3 5:05 PM
 */
public class EasyCountDownLatch {
    private final Sync sync;

    public EasyCountDownLatch(int count) {
        this.sync = new Sync(count);
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            setState(count);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 0) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int c = getState();
                if (c == 0) {
                    return false;
                }
                int nextc = c - 1;
                if (compareAndSetState(c, nextc)) {
                    return nextc == 0;
                }
            }
        }
    }

    public void await() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

}
