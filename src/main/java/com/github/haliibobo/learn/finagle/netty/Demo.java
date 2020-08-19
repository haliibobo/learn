package com.github.haliibobo.learn.finagle.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-08-19 14:45
 * @description describe what this class do
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap=new ServerBootstrap();
        NioEventLoopGroup boss=new NioEventLoopGroup();
        NioEventLoopGroup work=new NioEventLoopGroup();
        bootstrap.group(boss,work);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new NioWebSocketChannelInitializer());
        Channel channel = bootstrap.bind(8081).sync().channel();


        channel.closeFuture().sync();
    }

}
