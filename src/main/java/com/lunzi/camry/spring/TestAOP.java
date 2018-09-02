package com.lunzi.camry.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/8/21 下午10:14
 */
@Aspect
@Component
public class TestAOP {
    @Before("execution(* com.lunzi.camry.spring.TestMethod.print(..))")
    public void before(JoinPoint joinPoint){

        System.out.println("before"+joinPoint.getSignature().getName());
    }

    @After("execution(* com.lunzi.camry.spring.TestMethod.print(..))")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    @Around("execution(* com.lunzi.camry.spring.TestMethod.print(..))")
    public void handle(ProceedingJoinPoint point) throws Throwable {
        Object result=null;
        System.out.println("--------");
        result=point.proceed();
        System.out.println("--------");
        System.out.println(result);

    }

    public void test(){
        System.out.println("this is test");
    }



}
