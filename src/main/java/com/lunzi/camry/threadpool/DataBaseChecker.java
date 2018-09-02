package com.lunzi.camry.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2018/7/21 下午4:02
 */
public class DataBaseChecker extends BaseHealthChecker {
    public DataBaseChecker(CountDownLatch countDownLatch) {
        super("dataBase", countDownLatch);
    }

    @Override
    public void verifyService() {
        try {
            System.out.println(this.getServiceName()+" start");
            Thread.sleep(5000);
            System.out.println(this.getServiceName()+" up");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
