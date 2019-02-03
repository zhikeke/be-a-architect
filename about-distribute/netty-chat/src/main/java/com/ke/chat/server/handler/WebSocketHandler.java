package com.ke.chat.server.handler;

import com.ke.chat.process.IMProcess;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * web socket 协议处理器
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private IMProcess process = new IMProcess();

    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        process.process(ctx.channel(), msg.text());
    }

    /**
     * 监听直接关闭浏览器事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 移除客户端
        process.logout(ctx.channel());
    }
}
