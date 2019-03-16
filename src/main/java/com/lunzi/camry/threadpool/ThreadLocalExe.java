package com.lunzi.camry.threadpool;


import com.lunzi.camry.domain.User;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2019/3/12 11:42 AM
 */
public class ThreadLocalExe {
    private static ThreadLocal<User> threadLocal = ThreadLocal.withInitial(() -> {
        User user = new User();
        user.setUserId(1L);
        return user;
    });

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    User user = threadLocal.get();
                    user.setUserId(2L);
                    threadLocal.set(user);
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        System.out.println(threadLocal.get().getUserId());
    }
}
