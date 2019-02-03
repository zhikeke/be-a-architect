package com.ke.rpc.consumer.proxy;

import com.ke.rpc.consumer.handler.RpcProxyHandler;
import com.ke.rpc.core.InvokeEntity;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxy {

    public static <T> T create(Class<T> clazz) {
        MethodProxy methodProxy = new MethodProxy(clazz);

        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, methodProxy);

        return result;
    }

}

class MethodProxy implements InvocationHandler {

    private Class<?> clazz;

    public MethodProxy(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果传进来的为一个已经实现了的类，直接调用该类
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {  // 远程服务调用
            return rpcInvoke(method, args);
        }
    }


    /**
     * Rpc 远程服务调用
     * @param method 方法
     * @param args 参数
     * @return
     */
    private Object rpcInvoke(Method method, Object[] args) {
        InvokeEntity entity = new InvokeEntity();
        entity.setClassName(this.clazz.getName());
        entity.setMethodName(method.getName());
        entity.setParams(method.getParameterTypes());
        entity.setValues(args);


        EventLoopGroup group = new NioEventLoopGroup();
        final RpcProxyHandler handler = new RpcProxyHandler();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 处理拆包、粘包编解码器
                                    .addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0 ,4))
                                    .addLast("frameEncoder",new LengthFieldPrepender(4))
                                    // 处理序列化的编解码器
                                    .addLast("encoder", new ObjectEncoder())
                                    .addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            // 添加自定义业务处理器
                            ch.pipeline().addLast("handler", handler);
                        }
                    });

            ChannelFuture f = bootstrap.connect("localhost",8080).sync();
            f.channel().writeAndFlush(entity).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

        return handler.getResult();
    }

}