package com.ke.handler;

import com.ke.http._HttpRequest;
import com.ke.http._HttpResponse;
import com.ke.servlets._Servlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class TomcatHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            _HttpRequest httpRequest = new _HttpRequest(ctx, request);
            _HttpResponse httpResponse = new _HttpResponse(ctx, request);

            new _Servlet().doGet(httpRequest, httpResponse);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
