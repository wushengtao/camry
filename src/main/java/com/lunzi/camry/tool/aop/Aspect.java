package com.lunzi.camry.tool.aop;

import com.lunzi.camry.tool.core.util.ReflectUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 切面抽象
 * Created by lunzi on 2018/7/19 上午8:20
 */
public abstract class Aspect implements InvocationHandler {
    private Object target;

    public Aspect(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return this.target;
    }

    /**
     * 执行前
     * @param target
     * @param method
     * @param args
     * @return
     */
    public abstract boolean before(Object target, Method method, Object[] args);

    /**
     * 执行后
     * @param target
     * @param methodm
     * @param args
     * @return
     */
    public abstract boolean after(Object target, Method methodm, Object[] args);

    /**
     * 异常处理
     * @param target
     * @param method
     * @param args
     * @param e
     * @return
     */
    public abstract boolean afterException(Object target,Method method,Object[] args,Throwable e);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object result=null;
        if(before(target,method,args)){
            try{
                result=ReflectUtil.invoke(target,method,args);
            }catch(Exception e){

            }
        }
        if(after(target, method, args)){
            return result;
        }
        return null;
    }
}
