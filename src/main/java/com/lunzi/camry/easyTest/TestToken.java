package com.lunzi.camry.easyTest;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by lunzi on 2019/5/15 9:50 PM
 */
public class TestToken {
    public static void testToken() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(10);
        Thread.sleep(1000);
        if (rateLimiter.tryAcquire(20)) {
            System.out.println("======== Time1:" + System.currentTimeMillis() / 1000);
        }
        Thread.currentThread().sleep(1001);
        if (rateLimiter.tryAcquire(1))//代码3
            System.out.println("======== Time2:" + System.currentTimeMillis() / 1000);

        if (rateLimiter.tryAcquire(5))
            System.out.println("======== Time3:" + System.currentTimeMillis() / 1000);

    }

    public static void main(String[] args) throws InterruptedException {
        testToken();
    }
}
