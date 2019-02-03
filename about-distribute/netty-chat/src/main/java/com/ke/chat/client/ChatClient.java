package com.ke.chat.client;

import com.ke.chat.client.handler.ChatClientHandler;
import com.ke.chat.protocol.IMDecoder;
import com.ke.chat.protocol.IMEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;


/**
 * 客户端
 */
public class ChatClient  {

    private String host;
    private int port;
    private String nickName;
    
    public ChatClient(String nickName){
        this.nickName = nickName;
    }
    
    public void connect(String host, int port){
    		this.host = host;
    		this.port = port;

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast(new IMEncoder())
                            .addLast(new IMDecoder())
                            .addLast(new ChatClientHandler(nickName));
                }
            });

            ChannelFuture f = b.connect(this.host, this.port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    
    
    public static void main(String[] args) throws IOException{
		new ChatClient("keke").connect("127.0.0.1",8888);
    }
    
}
