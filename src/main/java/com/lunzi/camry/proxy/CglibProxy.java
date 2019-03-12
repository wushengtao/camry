package com.lunzi.camry.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lunzi on 2019/3/10 5:15 PM
 */
public class CglibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);

    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj=methodProxy.invokeSuper(o,objects);
        after();
        return obj;
    }

    public void before() {
        System.out.println("bebore");
    }

    public void after() {
        System.out.println("after");
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy=new CglibProxy();
        cglibProxy.getProxy(Man.class).say();
    }
}
