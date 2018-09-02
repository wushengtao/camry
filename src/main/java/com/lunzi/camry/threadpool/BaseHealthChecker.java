package com.lunzi.camry.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * 抽象的健康检查
 * Created by lunzi on 2018/7/21 下午3:42
 */
public abstract class BaseHealthChecker implements Runnable{
    private String serviceName;
    private boolean isUp;
    private CountDownLatch countDownLatch;

    public BaseHealthChecker(String serviceName,CountDownLatch countDownLatch){
        this.serviceName=serviceName;
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run() {
        try{
            verifyService();
            isUp=true;
        }catch(Throwable throwable){
            isUp=false;
        }finally {
            if(null!=countDownLatch){
                countDownLatch.countDown();
            }
        }

    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isUp() {
        return isUp;
    }

    public abstract void verifyService();
}
