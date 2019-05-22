package com.lunzi.camry.proxy;

import com.google.common.collect.Maps;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by lunzi on 2019/5/10 3:45 PM
 */
public class ProxyFactory implements InvocationHandler {
    private Object human;

    public ProxyFactory(Object human) {
        this.human = human;
    }

    @SuppressWarnings("unchecked")
    public Object getObject() {
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{IHuman.class,EchoService.class}, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("before");
//        if(method.getName().equals("$echo")){
//            return args[0];
//        }
        Object obj = method.invoke(human, args);
        System.out.println("after");
        return obj;
    }

    public static void main(String[] args) {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        ProxyFactory proxyFactory = new ProxyFactory(new Man());
//        EchoService human = (EchoService) proxyFactory.getObject();
//        System.out.println(human.$echo("1232"));
        Map<Long,String> map= Maps.newHashMap();
        System.out.println(map.get(null));
    }
}
