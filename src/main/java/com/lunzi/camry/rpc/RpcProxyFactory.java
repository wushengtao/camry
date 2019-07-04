package com.lunzi.camry.rpc;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;


/**
 * Created by lunzi on 2019/6/26 9:18 AM
 */
@Slf4j
public class RpcProxyFactory implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8888);
            int a = (int) args[0];
            int b = (int) args[1];
            RpcInvoker rpcInvoker = new RpcInvoker(a, b);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcInvoker);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("开始远程调用");
        log.info("远程调用结束");
        return 3;
    }
}
