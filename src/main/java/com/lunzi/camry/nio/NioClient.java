package com.lunzi.camry.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by lunzi on 2018/8/6 下午10:37
 */
public class NioClient {
    private Selector selector;

    public void init(String ip, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        this.selector = Selector.open();
        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void listen() throws IOException {
        System.out.println("client started");
        while (true) {
            selector.select();//阻塞
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("客户端请求连接服务器端.").getBytes()));
                    // 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    read(key);
                }
                    
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data);
        System.out.println("客户端收到服务器信息:" + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(new String("客户端发送服务器消息: hello").getBytes());
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws IOException {
        NioClient nioClient=new NioClient();
        nioClient.init("localhost",8800);
        nioClient.listen();
    }
}


