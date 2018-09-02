package com.lunzi.camry.generics;

import com.lunzi.camry.service.TestService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by lunzi on 2018/7/18 上午8:43
 */
public class TestGenerics {
    public static  void print(Object object){
        String a="123";
        Integer b=123;
        Object oa=TestGenerics.newInstance(a);
        TestGenerics.newInstance(b);

    }

    public static void main(String[] args) {
        String str="123";
        print(str);
    }
    public static <T> T newProxyInstance(ClassLoader classloader, InvocationHandler invocationHandler, Class<?>... interfaces) {
        return (T) Proxy.newProxyInstance(classloader, interfaces, invocationHandler);
    }
    public static <T> T newInstance(Object obj){
        return (T) obj;
    }

}
