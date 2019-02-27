package com.lunzi.camry.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * human代理
 * Created by lunzi on 2019/2/26 4:05 PM
 */
public class HumanProxy implements IHuman{
    private IHuman human;
    public HumanProxy(IHuman human){
        this.human=human;
    }
    @Override
    public void say() {
        printLog();
        human.say();
    }
    private void printLog(){
        System.out.println("this is the log");
    }

    public static void main(String[] args) {
        //静态代理
        //HumanProxy humanProxy=new HumanProxy(new Man());
        //humanProxy.say();
        //动态代理
        IHuman human= (IHuman) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IHuman.class},new LogInvocationHandler(new Man()));
        human.say();
    }
    static class LogInvocationHandler implements InvocationHandler{
        private IHuman human;
        private LogInvocationHandler(IHuman human){
            this.human=human;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log();
            Object result=method.invoke(human,args);
            return result;
        }
        private void log(){
            System.out.println("this is the log");
        }
    }
}
