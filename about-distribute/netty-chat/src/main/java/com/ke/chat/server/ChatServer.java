package com.ke.chat.server;

import com.ke.chat.protocol.IMDecoder;
import com.ke.chat.protocol.IMEncoder;
import com.ke.chat.server.handler.HttpHandler;
import com.ke.chat.server.handler.SocketHandler;
import com.ke.chat.server.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


public class ChatServer {

    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() {
        // Boss 线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker 线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                          protected void initChannel(SocketChannel socketChannel) throws Exception {
                              // 业务处理

                              // 自定义协议
                              socketChannel.pipeline()
                                      .addLast(new IMEncoder())
                                      .addLast(new IMDecoder())
                                      .addLast(new SocketHandler());

                              // 解码和编码Http 请求
                              socketChannel.pipeline()
                                      .addLast(new HttpServerCodec())
                                      .addLast(new HttpObjectAggregator(64 * 1024))
                                      // 处理文件流
                                      .addLast(new ChunkedWriteHandler())
                                      // Http 请求处理
                                      .addLast(new HttpHandler());


                              // Web Socket 协议处理
                              socketChannel.pipeline()
                                      .addLast(new WebSocketServerProtocolHandler("/im"))
                                      .addLast(new WebSocketHandler());

                          }
                    });

            // 等待客户端连接
            ChannelFuture channelFuture = serverBootstrap.bind(this.port).sync();

            System.out.println("Netty Server Start Success for port: " + this.port);

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ChatServer(8888).start();
    }

}
