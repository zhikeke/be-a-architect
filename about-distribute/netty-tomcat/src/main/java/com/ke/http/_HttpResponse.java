package com.ke.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

public class _HttpResponse {

    private ChannelHandlerContext context;

    private HttpRequest request;

    public _HttpResponse(ChannelHandlerContext context, HttpRequest request) {
        this.context = context;
        this.request = request;
    }

    public void write(String content) {
        if (null == content || "".equals(content)) {
            return;
        }

        try {
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(content.getBytes("UTF-8")));

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/json");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.EXPIRES, 0);

            if (HttpUtil.isKeepAlive(request)) {
                response.headers().set("Connection", "keep-alive");
            }

            context.writeAndFlush(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
