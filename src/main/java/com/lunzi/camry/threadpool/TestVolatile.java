package com.lunzi.camry.threadpool;

import com.lunzi.camry.redis.RedisConfig;

import javax.annotation.security.RunAs;
import java.io.File;
import java.time.Duration;

/**
 * Created by lunzi on 2018/12/5 9:03 AM
 */
public class TestVolatile {
    public static void main(String[] args) {
        String str="12%";
        Double d=Double.valueOf(str.replace("%",""));
        System.out.println(d/100);
    }
}

