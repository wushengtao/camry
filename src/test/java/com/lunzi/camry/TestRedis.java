package com.lunzi.camry;

import com.lunzi.camry.redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lunzi on 2019/2/21 6:38 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {
    @Test
    public void testGetLock() throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(10);
       for(int i=0;i<10;i++){
           Thread thread=new Thread(()->{
               RedisLock.tryLcok("lock","wushengtao",1);
           });
           thread.start();
           latch.countDown();
       }
       latch.await();
    }

}
