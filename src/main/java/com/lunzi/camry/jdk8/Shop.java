package com.lunzi.camry.jdk8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2019/3/11 7:12 PM
 */
public class Shop {
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getAysnPrice(String product) {
        //异步计算
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        return futurePrice.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();//延迟
        throw new RuntimeException("error happened");
        //return 1.0;
    }

    public void doSomethingElse() {
        delay();
    }

    public static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void findPrice(String product) {
          List<Shop> shops = Lists.newArrayList(new Shop(), new Shop(), new Shop(), new Shop(),new Shop());
        //并行流效率提升四倍
        shops.stream().parallel().map(shop -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            return "123";
        }).collect(Collectors.toList());

        //使用异步方法
//        List<CompletableFuture<String>> completableFutureList = Lists.newArrayList();
//        completableFutureList=shops.stream().map(shop -> CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "";
//        })).collect(Collectors.toList());
//        completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }

    public static void main(String[] args) {
        //获取一个代理
        Shop shop = new ShopProxy().getProxy(Shop.class);
        shop.findPrice("test");
    }
}
