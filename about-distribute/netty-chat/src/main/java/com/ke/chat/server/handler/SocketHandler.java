package com.ke.chat.server.handler;

import com.ke.chat.process.IMProcess;
import com.ke.chat.protocol.IMMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SocketHandler extends SimpleChannelInboundHandler<IMMessage> {
    IMProcess process = new IMProcess();

    protected void channelRead0(ChannelHandlerContext ctx, IMMessage msg) throws Exception {
        process.process(ctx.channel(), msg);
    }
}
