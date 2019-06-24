package com.lunzi.camry.easyThreadPool;

/**
 * Created by lunzi on 2019/5/23 3:13 PM
 */
public class TestEasyThreadPool {
    public static void main(String[] args) {
        EasyExecutorService easyExecutorService=EasyExecutors.newFixedThreadPool(6);
        easyExecutorService.submit(()->{
            try {
                System.out.println("开始执行任务");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
}
