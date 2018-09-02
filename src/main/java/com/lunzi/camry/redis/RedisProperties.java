package com.lunzi.camry.redis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhouxiaoliu on 2017/4/20.
 */
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host;
    private int port;
    private String password;
    private int timeout;
    private int maxIdle;
    private long maxWaitMillis;
}
