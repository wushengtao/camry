package com.lunzi.camry.rpc;

import com.lunzi.camry.domain.Student;
import com.lunzi.camry.nettys.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lunzi on 2018/9/5 下午10:16
 */
public class RpcServer {
    private static int port = 7777;
    private static String address = "localhost";

//    public static void main(String args[]) throws Exception {
//        ServerSocket serverSocket = new ServerSocket(port);
//        ExecutorService executorService=Executors.newFixedThreadPool(10);
//        int i = 0;
//        while (true) {
//            //System.out.println("等待服务器");
//            Long start=System.currentTimeMillis();
//            Socket socket = serverSocket.accept();//这里阻塞在这里哦
//            Long end=System.currentTimeMillis();
//            System.out.println(socket.toString());
//            //读取
////            byte [] b=new byte[1024];
////            BufferedInputStream bufferedInputStream=new BufferedInputStream(socket.getInputStream());
////            StringBuilder stringBuilder=new StringBuilder();
////            int i=0;
////            while((i=bufferedInputStream.read())!=-1){
////                stringBuilder.append((char)i);
////            }
////            System.out.println(stringBuilder.toString());
//            //读取对象
//            //处理的逻辑采用多线程
//            Thread thread =new Thread(new ServerThread(socket));
//            executorService.submit(thread);
////            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());) {
////                Student student = (Student) inputStream.readObject();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//
//
//        }
//
//    }
    //换成netty的实现方式
    public static void main(String agrs[]){
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(boss,work);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new DiscardServerHandler());
            }
        });
    }

}

class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("处理逻辑");
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());) {
            Student student = (Student) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

