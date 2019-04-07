package com.lunzi.camry.aop;

import com.lunzi.camry.lock.ReentLock;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentLockAspect 切面
 * Created by lunzi on 2019/4/1 3:58 PM
 */
@Aspect
@Component
public class ReentLockAspect {
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Pointcut("@annotation(com.lunzi.camry.lock.ReentLock)")
    public void reentLock() {
    }

    //前置校验
    @Before("reentLock()")
    public void doBefore(JoinPoint joinPoint) {
        //先获取锁
        getReentLock();
        //执行业务逻辑 等待1秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //释放锁
        releaseLock();
    }

    /**
     * 释放锁
     *
     * @return
     */
    private  void releaseLock() {
        System.out.println("----解锁----");
        reentrantLock.unlock();
    }


    /**
     * 获取锁
     *
     * @return
     */
    private synchronized void getReentLock() {
        System.out.println("----加锁----");
        reentrantLock.lock();
    }

}
