package com.lunzi.camry.nio;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by lunzi on 2019/4/28 1:47 PM
 */
@Slf4j
public class NIOServer {
    private static Selector selector;

    public static void main(String args[]) throws IOException, InterruptedException {
        //新建一个channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//非阻塞的
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("listen on 8888");
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //select
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handlekey(selectionKey);
            }
        }
    }

    private static void handlekey(SelectionKey selectionKey) throws IOException {
        //连接就绪
        if (selectionKey.isAcceptable()) {
            handleAccept(selectionKey);
        }

        //读就绪
        if (selectionKey.isReadable()) {
            handleRead(selectionKey);
        }

        //写就绪
        if (selectionKey.isWritable()) {
            handleWrite(selectionKey);
        }
    }

    private static void handleWrite(SelectionKey selectionKey) {
    }

    private static void handleRead(SelectionKey selectionKey) throws IOException {
        System.out.println("handleRead");
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        if(socketChannel.read(byteBuffer)==-1){

        }
        String text = new String(byteBuffer.array(), "utf-8");
        System.out.println(text);
    }

    private static void handleAccept(SelectionKey selectionKey) throws IOException {
        System.out.println("isAcceptable");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
