package com.ke;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 模拟 NIC
 */
public class NioServer {
    /**
     * 连接端口号
     */
    private int port;

    private InetSocketAddress inetSocketAddress = null;

    private Selector selector;

    public NioServer() {

    }

    public NioServer(int port) {
        this.port = port;
        inetSocketAddress = new InetSocketAddress(this.port);

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(inetSocketAddress);

            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();

            // 注册监听连接时间
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器初始化完毕,开始工作,监听端口为: " + this.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听事件
     */
    private void listen() {
         try {
             while (true) {
                 int wait = this.selector.select();

                 if (wait == 0) {
                     continue;
                 }

                 Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
                 Iterator<SelectionKey> keys = selectionKeys.iterator();

                 while (keys.hasNext()) {
                     SelectionKey key = keys.next();

                     process(key);

                     keys.remove();
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    /**
     * 处理流程
     * @param key
     */
    private void process(SelectionKey key) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);

            // 注册监听读事件
            socketChannel.register(this.selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int len = socketChannel.read(buffer);

            if (len > 0) {
                buffer.flip();

                String content = new String(buffer.array(), 0, len);
                System.out.println(content);

                // 注册监听写事件
                socketChannel.register(this.selector, SelectionKey.OP_WRITE);

                buffer.clear();
            }
        } else if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();

            socketChannel.write(buffer.wrap("Hello World".getBytes()));

            socketChannel.close();
        }

    }

    public static void main(String[] args) {
        new NioServer(80).listen();
    }


}
