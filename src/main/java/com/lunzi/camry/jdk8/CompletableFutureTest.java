package com.lunzi.camry.jdk8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2019/2/28 4:35 PM
 */
public class CompletableFutureTest {
    public static Integer getInt() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static Double getDouble(Integer num) {
        return Double.valueOf(num);
    }

    public static Double add(Double num) {
        return num++;
    }

    public static CompletableFuture<String> ask() {
        final CompletableFuture<String> future = new CompletableFuture<>();
        return future;
    }

    public static void testAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                =CompletableFuture.supplyAsync(()->"Hello")
                .thenApply(s->s+"world");

    }

    public static void testExcep(){

    }
    public static Integer cal(Integer num){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num;
    }
    public static Future<String> calculateAsync(Integer num) throws InterruptedException {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(5000);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public static void testAllof() throws ExecutionException, InterruptedException {
        CompletableFuture future1=CompletableFuture.supplyAsync(()->{
            System.out.println("1231231");
            return null;
        });
        List<Integer> list= Lists.newArrayList();
        list=null;
        list.stream().map(Integer::intValue).collect(Collectors.toList());
        //CompletableFuture.allOf(future1).join();

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture future=CompletableFuture.supplyAsync(()->{
            System.out.println("task1。。。。。");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        future.whenComplete((result,excecption)->{
            System.out.println("finish..");
        });
        while(true){
            Thread.sleep(1000);
            System.out.println("other task");
        }
    }
}
