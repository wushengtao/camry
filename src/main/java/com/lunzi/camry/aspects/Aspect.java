package com.lunzi.camry.aspects;

import com.lunzi.camry.service.TestService;
import com.lunzi.camry.service.impl.TestServiceImpl;
import org.apache.ibatis.io.ResolverUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lunzi on 2018/6/10 下午2:13
 */
public abstract class Aspect implements InvocationHandler {
    private Object target;

    public Aspect(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return this.target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object object=method.invoke(target,args);
        return object;
    }
    public static void main(String args[]){

    }
}

