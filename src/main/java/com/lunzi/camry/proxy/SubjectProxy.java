package com.lunzi.camry.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lunzi on 2018/7/18 上午7:59
 */
public class SubjectProxy implements InvocationHandler {
    private ISubject subject;//需要被代理的类
    public SubjectProxy(ISubject subject){
        this.subject=subject;//构造函数注入需要被代理的类
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before print");
        method.invoke(subject,args);
        System.out.println("after method");
        return null;
    }
    public static void main(String args[]){
       ISubject subject=new SubjectImpl();
       SubjectProxy subjectProxy=new SubjectProxy(subject);
       ISubject s=SubjectProxy.newProxyInstance(subject.getClass().getClassLoader(),subjectProxy,subject.getClass().getInterfaces());
       s.print();
    }
    public static <T> T newProxyInstance(ClassLoader classLoader,InvocationHandler invocationHandler,Class<?>... interfaces){
        return (T)Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }

}
