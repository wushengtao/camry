package com.lunzi.camry.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2018/7/21 下午3:54
 */
public class NetWorkChecker extends BaseHealthChecker {
    public NetWorkChecker(CountDownLatch countDownLatch) {
        super("netWork", countDownLatch);
    }

    @Override
    public void verifyService() {
        try {
            System.out.println(this.getServiceName()+" start");
            Thread.sleep(3000);
            System.out.println(this.getServiceName()+" up");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
