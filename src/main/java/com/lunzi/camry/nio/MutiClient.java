package com.lunzi.camry.nio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * socket 客户端
 * Created by lunzi on 2019/2/25 9:16 AM
 */
public class MutiClient implements Runnable{

    @Override
    public void run() {
        Socket socket=new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost",8888));
            BufferedWriter bufferedWriter=new BufferedWriter(new PrintWriter(socket.getOutputStream()));
            bufferedWriter.write("hello");
            bufferedWriter.flush();
            bufferedWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es= Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++){
            es.submit(new MutiClient());
        }
    }
}
