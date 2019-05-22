package com.lunzi.camry.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2019/5/18 9:40 PM
 */
@Aspect
@Configuration
public class CountRunTimeAspect {
    @Pointcut("@annotation(com.lunzi.camry.aop.CountRunTime)")
    public void count() {

    }

    @Around("count()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //起始时间
        long startTimed = System.currentTimeMillis();
        //执行业务逻辑
        //获取执行的方法
        String methodName = proceedingJoinPoint.getSignature().getName();
        //放大一下 调用10000次
        Object obj = null;
        obj = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println(methodName + "cost:" + (endTime - startTimed));
        return obj;
    }

}
