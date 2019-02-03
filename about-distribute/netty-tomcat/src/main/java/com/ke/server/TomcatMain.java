package com.ke.server;

import com.ke.handler.TomcatHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

public class TomcatMain {
    public void start(int port) throws Exception{
        // Boss 线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // Worker 线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // Netty 服务
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    // 主线程处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 业务逻辑处理
                        protected void initChannel(SocketChannel client) throws Exception {
                            client.pipeline()
                                    // 编码器
                                    .addLast(new HttpRequestEncoder())
                                    // 解码器
                                    .addLast(new HttpRequestDecoder())
                                    // 业务逻辑处理
                                    .addLast(new TomcatHandler());
                        }
                    })
                    // 配置信息
                    .option(ChannelOption.SO_BACKLOG, 128) // 主线程配置信息
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  // 子线程配置信息

            //绑定服务端口
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            System.out.println("Tomcat Server startup for port:" + port);

            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws Exception{
        new TomcatMain().start(8888);
    }
}
