package com.lunzi.camry;

import com.lunzi.camry.redis.RedisLock;
import com.lunzi.camry.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lunzi on 2019/2/21 6:38 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {
    @Autowired
    private TestService testService;
    @Test
    public void testGetLock() throws InterruptedException {
    }
    @Test
    public void testLockAnnotation(){
        testService.lock();
    }

    @Test
    public void testZK() throws BrokenBarrierException, InterruptedException {
        //新建两个线程
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        Thread t1=new Thread(()->{
            testService.lock();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread t2=new Thread(()->{
            try {
                testService.lock();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        cyclicBarrier.await();
    }



}
