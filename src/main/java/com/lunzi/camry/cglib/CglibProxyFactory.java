package com.lunzi.camry.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理工厂
 * Created by lunzi on 2018/10/8 下午2:39
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;
    public CglibProxyFactory(Object target){
        this.target=target;
    }
    public Object getProxyInstance(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //代理一下add方法
        if(method.getName().equals("add")){
            System.out.println("开始代理");
            Object invoke=method.invoke(target,args);
            return invoke;
        }else {
            return method.invoke(target,args);
        }
    }
    public static void main(String args[]){
        UserDao userDao=new UserDao();
        UserDao proxy= (UserDao) new CglibProxyFactory(userDao).getProxyInstance();
        proxy.add();
        proxy.update();
    }
}
