package com.lunzi.camry;

import com.lunzi.camry.redis.RedisLock;
import com.lunzi.camry.service.TestService;
import com.mhc.framework.support.lock.DistributedLock;
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
    @Autowired
    private DistributedLock distributedLock;
    @Autowired
    private TestService testService;
    @Test
    public void testGetLock() throws InterruptedException {
//        Boolean result=distributedLock.lock("dislock",300000,5,50);
//        if(result){
//            System.out.println("加锁成功");
//        }else {
//            System.out.println("加锁失败");
//        }

    }
    @Test
    public void testLockAnnotation(){
        testService.lock();
    }

}
