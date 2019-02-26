package com.lunzi.camry.nio;

import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.SelectKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio 服务端
 * Created by lunzi on 2019/2/25 1:51 PM
 */
public class MutiNioServer {
    private Selector selector;
    private ExecutorService es = Executors.newCachedThreadPool();
    private static Map<Socket, Long> map = Maps.newHashMap();

    //开始服务器
    public void startServer() throws IOException {
        //获取select实例
        selector = SelectorProvider.provider().openSelector();
        //服务端获取SocketChannel实例
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        InetSocketAddress isa = new InetSocketAddress(8888);
        serverSocketChannel.socket().bind(isa);
        //注册事件accept事件 selector 为channel服务
        SelectionKey appcetkey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环
        for (; ; ) {
            selector.select();
            //获取准备好的channel
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            //获取迭代器,循环set
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) it.next();
                //移除，不能重复的处理
                it.remove();
                if (selectionKey.isAcceptable()) {
                    //可处理的accept
                    processAccept(selectionKey);
                } else if (selectionKey.isValid() && selectionKey.isReadable()) {
                    //判断是否为可读的状态
                    map.put(((SocketChannel) selectionKey.channel()).socket(), System.currentTimeMillis());
                    processRead(selectionKey);
                } else if (selectionKey.isValid() && selectionKey.isWritable()) {
                    proccessWrite(selectionKey);
                    long start=map.get(((SocketChannel) selectionKey.channel()).socket());
                    long end=System.currentTimeMillis();
                    System.out.println("spend："+(end-start));
                }
            }
        }
    }

    private void proccessWrite(SelectionKey selectionKey) {

    }

    //处理读取
    private void processRead(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer bb = ByteBuffer.allocate(1024);
        int len = 0;
        try {
            len = socketChannel.read(bb);
            if(len==-1){
                selectionKey.channel().close();
                selectionKey.cancel();
                return ;
            }
            bb.flip();
            es.submit(new HandleMsg(selectionKey,bb));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理客户端和服务端的连接
    private void processAccept(SelectionKey selectionKey) {
        //获取对应的channel
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        try {
            //获取客户端的连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);//非阻塞模式
            //注册感兴趣的事件 只对读的事件感兴趣
            System.out.println(socketChannel.socket().getRemoteSocketAddress()+"connect！");
            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class HandleMsg implements Runnable {
        private SelectionKey selectionKey;
        private ByteBuffer bb;

        public HandleMsg(SelectionKey selectionKey, ByteBuffer bb) {
            this.selectionKey = selectionKey;
            this.bb = bb;
        }

        @Override
        public void run() {
            byte[] datas=new byte[bb.remaining()];
            bb.get(datas);
            try {
                System.out.println(new String(datas,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //处理逻辑获取客户端发来的hello
            selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }

    public static void main(String[] args) throws IOException {
        MutiNioServer mutiNioServer=new MutiNioServer();
        mutiNioServer.startServer();
    }
}
