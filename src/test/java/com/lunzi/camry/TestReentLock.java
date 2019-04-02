package com.lunzi.camry;

import com.lunzi.camry.lock.ReentLock;
import com.lunzi.camry.lock.ReentLockTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Created by lunzi on 2019/4/1 3:46 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestReentLock {
    @Autowired
    ReentLockTest reentLockTest;

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        //100个线程
        IntStream.range(0, 5).forEach((count) -> {
            new Thread(() -> {
                reentLockTest.add();
                countDownLatch.countDown();
            }).start();
        });
        countDownLatch.await();
        System.out.println(reentLockTest.i);
    }
}
