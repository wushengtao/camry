package com.lunzi.camry.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * Created by lunzi on 2019/3/24 11:52 PM
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfigs {
    /**  
      * <p> 实例化 RedisTemplate 对象 </p>
     * 
      * @param redisConnectionFactory
      * @return RedisTemplate<String, Object>
      */
    @Bean
    public RedisTemplate<Serializable, Serializable> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**  
      * <p> 设置数据存入 redis 的序列化方式,并开启事务 </p>
     * 
      * @param redisTemplate
      * @param factory
      */
    private void initDomainRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate, RedisConnectionFactory factory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        //redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
}
