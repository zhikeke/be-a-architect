package com.ke.chat.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;

/**
 * Http 协议处理器
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final String WEB_ROOT = "webroot";

    private static URL BASE_URL = HttpHandler.class.getProtectionDomain().getCodeSource().getLocation();

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
          // 获取客户端请求uri
        String uri = fullHttpRequest.uri();

        String page = uri.equals("/") ? "/chat.html" : uri;

        String contentType = "text/html;charset=UTF-8";

        if (page.endsWith(".css")) {
            contentType = "text/css;charset=UTF-8";
        } else if (page.endsWith(".js")) {
            contentType = "application/javascript;charset=UTF-8";
        } else if (uri.toLowerCase().matches("(jpg|png|gif)$")) {
            String ext = uri.substring(uri.lastIndexOf("."));
            contentType = "image/" +ext + ";";
        }

        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(getFileFromRoot(page), "r");
        } catch (Exception e) {
            channelHandlerContext.fireChannelRead(fullHttpRequest.retain());
            return;
        }

        HttpResponse response = new DefaultHttpResponse(fullHttpRequest.protocolVersion(), HttpResponseStatus.OK);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);

        boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);

        if (keepAlive) {
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        channelHandlerContext.write(response);

        channelHandlerContext.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));

        // 清空缓存区
        ChannelFuture future = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

        file.close();
    }

    private File getFileFromRoot(String fileName) throws Exception{
        String path = BASE_URL.toURI() + WEB_ROOT + fileName;

        path = path.startsWith("file:") ? path.substring(6) : path;

        return new File(path.replaceAll("//", "/"));
    }

}
