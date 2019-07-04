package com.lunzi.camry.rpc;

import java.lang.reflect.Proxy;

/**
 * Created by lunzi on 2019/6/26 9:07 AM
 */
public class RpcClient {
    public static void main(String[] args) {
        //创建代理对象
        RpcInterface rpcInterface = (RpcInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{RpcInterface.class},new RpcProxyFactory());
        rpcInterface.add(1, 2);
    }
}
