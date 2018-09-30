package com.lunzi.camry.redis;

import com.lunzi.camry.spring.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * Created by lunzi on 2018/7/18 下午7:37
 */
public class TestRedis {
    public static void main(String args[]) {
        RedisCacheTemplate redisCacheTemplate = new RedisCacheTemplate();
        String key = "test";
        String lock = "lock";
        for (int i = 0; i < 100; i++) {
            //多线程去测试
            String uuidString = UUID.randomUUID().toString();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        boolean flag = redisCacheTemplate.getDistributedLockV2("testV2", "test", 5);
                        if (flag) {
                            System.out.println(Thread.currentThread().getName() + "获取成功");
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName() + "获取失败，重新获取");
                        }
                    }
                }
            });
            thread.start();
            //Boolean flag=redisCacheTemplate.getDistributedLockV2("testV2",uuidString,5);
            //Boolean flag=redisCacheTemplate.getDistributedLock(key,5);
        }
    }
}
