package com.lunzi.camry.nio;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by lunzi on 2018/8/6 下午10:23
 */
public class NioServer {
    private Selector selector;
    public void init(int port) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);//非阻塞
        this.selector=Selector.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
    }

    /**
     * 轮训的方式进行监听
     */
    public void listen() throws IOException {
        System.out.println("nio server started");
        while(true){
            selector.select();//
            Iterator<SelectionKey> it=selector.selectedKeys().iterator();
            while(it.hasNext()){
                SelectionKey selectionKey=it.next();
                it.remove();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel server= (ServerSocketChannel) selectionKey.channel();
                    SocketChannel channel=server.accept();
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("server accept request").getBytes()));
                    channel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    read(selectionKey);
                }
            }
        }
    }

    private void read(SelectionKey selectionKey) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data);
        System.out.println("服务器端收到客户端信息:" + msg);
        // 将消息回送给客户端
        ByteBuffer outBuffer = ByteBuffer.wrap(new String("服务器端发送客户端消息: world.").getBytes());
        channel.write(outBuffer);
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer=new NioServer();
        nioServer.init(8800);
        nioServer.listen();
    }
}
