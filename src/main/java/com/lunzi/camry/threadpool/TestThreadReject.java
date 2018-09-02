package com.lunzi.camry.threadpool;

import java.util.concurrent.*;

/**
 * Created by lunzi on 2018/7/24 下午5:40
 */
public class TestThreadReject {
    public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService=
                new ThreadPoolExecutor(5, 5,
                        0, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(10),
                        new RejectedExecutionHandler() {
                            @Override
                            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                System.out.println(r.toString()+"is discard!");
                            }
                        });
        for(int i=0;i<Integer.MAX_VALUE;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread.sleep(10);
        }

    }
}
