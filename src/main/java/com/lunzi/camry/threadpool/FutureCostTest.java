package com.lunzi.camry.threadpool;

import java.util.concurrent.*;

/**
 * Created by lunzi on 2019/4/10 9:51 AM
 */
public class FutureCostTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Future future=executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                //耗时三秒的操作
                Thread.sleep(3000);
                return null;
            }
        });
        CompletableFuture completableFuture=CompletableFuture.supplyAsync(() -> {
            //耗时三秒的操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        completableFuture.whenComplete((o, o2) -> System.out.println("结果:"+o));
        Long end=System.currentTimeMillis();
        System.out.println("总耗时:"+(end-start));
        new CountDownLatch(1).await();
    }
}
