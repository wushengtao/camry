package com.lunzi.camry.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lunzi on 2018/7/21 下午4:04
 */
public class MainHealthCheker {
    public boolean start(){
        List<BaseHealthChecker> healthCheckerList=new ArrayList<>();
        CountDownLatch countDownLatch=new CountDownLatch(2);
        healthCheckerList.add(new NetWorkChecker(countDownLatch));
        healthCheckerList.add(new DataBaseChecker(countDownLatch));
        //这里用一个线程池
        ExecutorService executorService= Executors.newFixedThreadPool(healthCheckerList.size());
        for(BaseHealthChecker checker:healthCheckerList){
            executorService.execute(checker);
        }
        //挂起
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(final BaseHealthChecker baseHealthChecker:healthCheckerList){
            if(!baseHealthChecker.isUp()){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MainHealthCheker mainHealthCheker=new MainHealthCheker();
        System.out.println(mainHealthCheker.start());
    }
}
