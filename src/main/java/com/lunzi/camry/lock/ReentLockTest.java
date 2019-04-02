package com.lunzi.camry.lock;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentLocktest
 * Created by lunzi on 2019/4/1 3:40 PM
 */
@Component
public class ReentLockTest {
    public static int i=0;
    /**
     * 模拟一个加锁的操作
     * 打了注解的加锁
     */
    @ReentLock(value = "orderNo")
    public  void add(){
        i++;
    }
}
