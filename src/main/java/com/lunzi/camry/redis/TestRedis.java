package com.lunzi.camry.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

/**
 * Created by lunzi on 2018/7/18 下午7:37
 */
public class TestRedis {
    static RedisCacheTemplate redisCacheTemplate=new RedisCacheTemplate();
    public static void main(String[] args) throws InterruptedException {
        while(true){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean flag=redisCacheTemplate.getDistributedLock("key",3);
                    if(flag){
                        System.out.println(Thread.currentThread().getName()+"获取到了锁");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("任务结束");
                    }
                }
            });
            thread.start();

        }
    }
}
