package com.lunzi.camry.threadpool;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lunzi on 2018/6/21 下午3:25
 */
public class TestThreadPool {
    //public static AtomicInteger i = new AtomicInteger(0);
    public static CountDownLatch latch=new CountDownLatch(10);
    public static final  ThreadLocal<Integer> threadlocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public static int i=0;
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        for (int m = 0; m < 10; m++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                        for (int n = 0; n < 1000; n++) {
                            threadlocal.set(threadlocal.get()+1);
                        }
                        System.out.println(threadlocal.get());
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        latch.await();
        System.out.println(i);
    }
}
class TestProtect{
    public TestProtect(){

    }
    protected int init(){
        return 0;
    }
}
