package com.ke;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 模拟 AIO
 */
public class AioServer {

    private int port = 80;

    public AioServer(int port) {
        this.port = port;
    }

    private void listen() {
        try {
            AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));

            serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel client, Object attachment) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    client.read(buffer);

                    String content = new String(buffer.array());

                    System.out.println(content);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("发生异常啦!" + exc.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        new AioServer(80).listen();

        System.in.read();
    }

}
