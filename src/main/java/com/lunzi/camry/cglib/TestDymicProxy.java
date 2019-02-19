package com.lunzi.camry.cglib;

import java.awt.print.Book;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lunzi on 2018/10/8 下午2:54
 */
class DymicProxy implements BookDao{
    @Override
    public void addBook() {
        System.out.println("add book");
    }
}
 class MyProxy implements InvocationHandler{
    private Object obj;
    public Object getProxyInstance(Object obj){
        this.obj=obj;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method");
        Object object=method.invoke(obj,args);
        return object;
    }
}
public class TestDymicProxy{
    public static void main(String args[]){
        DymicProxy dymicProxy= new DymicProxy();
        MyProxy myProxy=new MyProxy();
        BookDao bookDao= (BookDao) myProxy.getProxyInstance(dymicProxy);
        bookDao.addBook();
    }
}

