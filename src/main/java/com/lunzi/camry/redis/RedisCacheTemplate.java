package com.lunzi.camry.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Created by lunzi on 2018/3/29 下午8:01
 */
@Slf4j
@Component
public class RedisCacheTemplate<K,V> {
   // @Autowired
    private JedisPool jedisPool=new JedisPool();
    /**
     * 获取一个连接
     */
    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            log.error("Redis Connect Error");
            throw new RuntimeException("Redis连接失败!");
        }
        return jedis;
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public String getString(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @param value
     * @param second (单位：秒)
     */
    public boolean setString(String key, String value, int second) {
        Jedis jedis = getJedis();
        String result = jedis.setex(key, second, value);
        jedis.close();
        if (result != null && "OK".equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获得制定key的map
     * @param key
     * @return
     */
    public Map<String,String> hgetAll(String key){
        Jedis jedis = getJedis();
        Map<String, String> result=jedis.hgetAll(key);
        jedis.close();
        return result;
    }

    /**
     * 插入指定key的map
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key,Map<String,String> map,int expireTime){
        Jedis jedis = getJedis();
        String result=jedis.hmset(key,map);
        jedis.expire(key,expireTime);//暂定超时时间为30分钟
        jedis.close();
        if (result != null && "OK".equals(result)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * @param key         key
     * @param lockSeconds 时间单位是秒
     * @return
     */
    public boolean getDistributedLock(String key, int lockSeconds) {
        Long nowTime = System.currentTimeMillis();//当前时间
        Long expiredTime = nowTime + lockSeconds * 1000;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            //尝试获取锁
            long setNxResult = jedis.setnx(key, String.valueOf(expiredTime));
            if (setNxResult == 1) {
                //获取成功 设置超时时间
                System.out.println("设置超时时间");
                jedis.expire(key, lockSeconds);
                return true;
            }
            //获取失败，说明存在锁，get一次
            return false;

        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }


    }

}
