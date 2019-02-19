package com.lunzi.camry.aop;

import com.lunzi.camry.annotation.BizResultAdvice;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by lunzi on 2019/1/30 10:36 AM
 */
@Component
@Aspect
@Slf4j
public class BizResultAspect {
    @Pointcut("@within(com.lunzi.camry.annotation.BizResultAdvice)||@annotation(com.lunzi.camry.annotation.BizResultAdvice)")
    public void bizMethod(){

    }
    @Around("bizMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        Boolean isAnno=methodSignature.getMethod().isAnnotationPresent(BizResultAdvice.class);
        System.out.println(isAnno);
        HashMap map=new HashMap<>();
        return joinPoint.proceed();
    }
}
