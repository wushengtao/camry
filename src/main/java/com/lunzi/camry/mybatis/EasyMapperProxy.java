package com.lunzi.camry.mybatis;

import sun.jvm.hotspot.debugger.Page;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lunzi on 2019/5/20 4:37 PM
 */
public class EasyMapperProxy<T> implements InvocationHandler {
    private EasySqlSession sqlSession;
    private Class<T> mapperInterface;
    public EasyMapperProxy(EasySqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession=sqlSession;
        this.mapperInterface=mapperInterface;

    }

    //代理调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            System.out.println("object 的方法不代理");
            return method.invoke(proxy,args);
        }
        //接口的默认方法也不代理 但是我不会写哈哈哈
        //下面代理真正需要执行的方法
        return method.invoke(proxy,args);
    }
}
