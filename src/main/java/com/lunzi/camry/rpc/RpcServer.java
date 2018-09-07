package com.lunzi.camry.rpc;

import com.lunzi.camry.domain.Student;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;

/**
 * Created by lunzi on 2018/9/5 下午10:16
 */
public class RpcServer {
    private static int port=7777;
    private static String address="localhost";
    public static void main(String args[]) throws Exception{
        ServerSocket serverSocket=new ServerSocket(port);
        while(true){
            System.out.println("等待服务器");
            Socket socket=serverSocket.accept();//这里阻塞在这里哦
            //读取
            byte [] b=new byte[1024];
            BufferedInputStream bufferedInputStream=new BufferedInputStream(socket.getInputStream());
            StringBuilder stringBuilder=new StringBuilder();
            int i=0;
            while((i=bufferedInputStream.read())!=-1){
                stringBuilder.append((char)i);
            }
            System.out.println(stringBuilder.toString());
            //读取对象
            ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
            Student student=(Student)inputStream.readObject();

        }

    }

}
