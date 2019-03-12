package com.lunzi.camry.jdk8;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理
 * Created by lunzi on 2019/3/11 9:43 PM
 */
public class ShopProxy implements MethodInterceptor {

    public  <T> T getProxy(Class<T> clazz){
       return (T)Enhancer.create(clazz,this);
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //前置方法
        long start=System.currentTimeMillis();
        Object obj=methodProxy.invokeSuper(o,objects);
        //后置放大
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        return obj;
    }
}
