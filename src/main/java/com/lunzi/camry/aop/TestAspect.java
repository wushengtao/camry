package com.lunzi.camry.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/10/8 下午4:29
 */

@Component
@Aspect
public class TestAspect {
    @Pointcut("@annotation(com.lunzi.camry.aop.RecordLog)")
    public void log(){
    }

    @Before("log()")
    public void beforeMethod(JoinPoint jp){
        System.out.println("this is before Method");
    }
    @After("log()")
    public void afterMethod(JoinPoint jp){
        System.out.println("this is after Method");
    }
}
