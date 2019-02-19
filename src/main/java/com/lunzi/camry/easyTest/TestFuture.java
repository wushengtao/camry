package com.lunzi.camry.easyTest;

import java.util.concurrent.*;

/**
 * Created by lunzi on 2018/11/15 4:24 PM
 */
public class TestFuture {
    ExecutorService es=Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Long startTime=System.currentTimeMillis();
        for(int i=0;i<5;i++){
            Long num=searchNum();
            System.out.println(num);
        }

        Long endTime=System.currentTimeMillis();
        System.out.println("执行的时间为："+(endTime-startTime));
    }
    public static Long searchNum() throws InterruptedException, ExecutionException {
        return searchNumFeature().get();
    }
    public static Future<Long> searchNumFeature(){
//        CompletableFuture<Long> completableFuture=new CompletableFuture<>();
//        new Thread(()->{
//            try {
//                Thread.sleep(1000);
//                int i=1/0;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            completableFuture.complete(1L);
//        }).start();
//        return completableFuture;
        return CompletableFuture.supplyAsync(()->{
            return 1L;
        });
    }

    public static Long calcu(){
        return 1L;
    }
}
