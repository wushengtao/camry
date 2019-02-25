package com.lunzi.camry.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.concurrent.TimeUnit;

/**
 * Created by lunzi on 2019/2/21 6:24 PM
 */
@Slf4j
public class RedisLock {
    private static JedisPool jedisPool = new JedisPool();
    //private static Jedis jedis=getJedis();

    /**
     * 获取一个连接
     */
    private static Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            log.error("Redis Connect Error");
            throw new RuntimeException("Redis连接失败!");
        }
        return jedis;
    }

    public static void tryLcok(String key, String value, int expireTime) {
        Jedis jedis = getJedis();
        //获取链接
        String result = jedis.set(key, value, "NX","EX",expireTime);
        System.out.println(result);
        //没有获取到
        jedis.close();
    }
}
