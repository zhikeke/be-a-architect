package com.ke.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpContentDecoder;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

public class _HttpRequest {

    private ChannelHandlerContext context;

    private HttpRequest request;

    public _HttpRequest(ChannelHandlerContext context, HttpRequest request) {
        this.context = context;
        this.request = request;
    }

    public String getUri() {
        return this.request.uri();
    }

    public String getMethod() {
        return this.request.method().name();
    }

    public Map<String, List<String>> getParams() {
        QueryStringDecoder decoder = new QueryStringDecoder(getUri());
        return decoder.parameters();
    }

    public String getParam(String name) {
        Map<String, List<String>> params = getParams();

        List<String> param = params.get(name);

        if (null == param) {
            return null;
        }

        return param.get(0);
     }
}
