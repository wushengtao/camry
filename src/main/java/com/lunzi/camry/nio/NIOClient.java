package com.lunzi.camry.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by lunzi on 2019/4/28 8:54 PM
 */
public class NIOClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8888));
        socketChannel.configureBlocking(false);
        for(int i=0;i<5;i++){
            Thread.sleep(1000);
            String text="hello"+i;
            ByteBuffer byteBuffer=ByteBuffer.wrap(text.getBytes());
            socketChannel.write(byteBuffer);
        }
        socketChannel.close();
    }
}
