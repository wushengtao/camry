package com.lunzi.camry.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lunzi on 2019/7/3 11:10 AM
 */
public class RpcServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            Socket socket=serverSocket.accept();
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            RpcInvoker rpcInvoker= (RpcInvoker) objectInputStream.readObject();
            System.out.println(rpcInvoker.getA());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
