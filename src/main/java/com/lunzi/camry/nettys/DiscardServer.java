package com.lunzi.camry.nettys;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by lunzi on 2018/7/13 上午9:02
 */
public class DiscardServer {
    private int port;
    private DiscardServer(int port){
        this.port=port;
    }
    public void run(){


    }
}
