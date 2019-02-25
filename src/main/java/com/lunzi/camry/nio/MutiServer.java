package com.lunzi.camry.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多个客户端连接
 * Created by lunzi on 2019/2/24 10:42 PM
 */
public class MutiServer {
    private static int port=8888;//端口号
    private ExecutorService es= Executors.newCachedThreadPool();
    private ServerSocket serverSocket;
    public void start(){
        try {
            serverSocket=new ServerSocket(port);
            while(true){
                Socket socket=serverSocket.accept();//等待连接
                System.out.println(socket.getRemoteSocketAddress()+"connect!");
                //获取连接就交给线程池去处理
                es.submit(new HandleMsg(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class HandleMsg implements Runnable{
        private Socket socket;
        public HandleMsg(Socket socket){
            this.socket=socket;
        }
        @Override
        public void run() {
            //处理逻辑获取客户端发来的hello
            try {
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str=bufferedReader.readLine();
                System.out.println(str);
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        MutiServer mutiServer=new MutiServer();
        mutiServer.start();
    }
}
