package com.lunzi.camry.redis;

import lombok.extern.slf4j.Slf4j;
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
        long now=System.currentTimeMillis();//当前时间
        long expireTime=now+lockSeconds*1000;
        //获取锁
        Jedis jedis=getJedis();
        Long setnxResult=jedis.setnx(key,String.valueOf(expireTime));
        try{
            //获取成功
            if(setnxResult==1){
                jedis.expire(key,lockSeconds);
                return true;
            }
            //获取失败
            String oldValue=jedis.get(key);
            if(oldValue==null){
                //重新设置
                if(jedis.setnx(key,String.valueOf(expireTime))==0){
                    return false;
                }
                jedis.expire(key,lockSeconds);
                return true;
            }
            //判断是否超期
            if(Long.parseLong(oldValue)>=now){
                //没有超期
                return false;

            }
            //已经超时了
            oldValue=jedis.getSet(key,String.valueOf(expireTime));
            if(oldValue!=null&&Long.parseLong(oldValue)>=now){
                return false;
            }
            jedis.expire(key,lockSeconds);
            return true;
        }finally {
           if(jedis!=null){
               jedis.close();
           }
        }
    }
    public boolean getDistributedLockV2(String lockName,String requestId,int expireTime){
        Jedis jedis=getJedis();
        try{
            //NX setnx PX毫秒 EX秒
            String nxResult=jedis.set(lockName,requestId,"NX","EX",expireTime);
            if("OK".equals(nxResult)){
                return true;
            }
            return false;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }

    }

}
