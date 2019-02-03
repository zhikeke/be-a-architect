package com.ke.chat.process;

import com.ke.chat.protocol.IMDecoder;
import com.ke.chat.protocol.IMEncoder;
import com.ke.chat.protocol.IMMessage;
import com.ke.chat.protocol.IMP;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

public class IMProcess {

    private static final ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final AttributeKey<String> NICK_NAME = AttributeKey.valueOf("nickName");
    private final AttributeKey<String> IP_ADDR = AttributeKey.valueOf("ipAddr");
    private final AttributeKey<String> ATTRS = AttributeKey.valueOf("attrs");

    private IMEncoder encoder = new IMEncoder();
    private IMDecoder decoder = new IMDecoder();


    public void logout(Channel channel) {
        onlineUsers.remove(channel);
    }

    public void process(Channel channel, IMMessage requestMsg) {
        if (null == requestMsg) {
            return;
        }

        // 登录
        if (IMP.LOGIN.getName().equals(requestMsg.getCmd())) {
            channel.attr(NICK_NAME).getAndSet(requestMsg.getSender());
            onlineUsers.add(channel);

            IMMessage responseMsg = null;

            for (Channel client : onlineUsers) {
                if (channel != client) {
                    responseMsg = new IMMessage(IMP.SYSTEM.getName(),
                            System.currentTimeMillis(),
                            onlineUsers.size(),
                            requestMsg.getSender() + " 加入了聊天室");
                } else {
                    responseMsg = new IMMessage(IMP.SYSTEM.getName(),
                            System.currentTimeMillis(),
                            onlineUsers.size(),
                            "成功加入聊天室");
                }

                client.writeAndFlush(new TextWebSocketFrame(encoder.encode(responseMsg)));
            }

            // 退出登录
        } else if (IMP.LOGOUT.getName().equals(requestMsg.getCmd())) {
            onlineUsers.remove(channel);

            // 聊天
        } else if (IMP.CHAT.getName().equals(requestMsg.getCmd())) {
            for (Channel client : onlineUsers) {
                if (channel != client) {
                    requestMsg.setSender(channel.attr(NICK_NAME).get());
                } else {
                    requestMsg.setSender("you");
                }

                client.writeAndFlush(new TextWebSocketFrame(encoder.encode(requestMsg)));
            }

            // 送花操作
        } else if(IMP.FLOWER.getName().equals(requestMsg.getCmd())) {
            long currentTime = requestMsg.getTime();
            String lastTimeStr = channel.attr(ATTRS).get();

            if (null != lastTimeStr && !"".equals(lastTimeStr)) {
                long lastTime = Long.valueOf(lastTimeStr);

                if ((lastTime + 10*1000) > currentTime) {
                    IMMessage responseMsg = new IMMessage();
                    responseMsg.setCmd(IMP.SYSTEM.getName());
                    responseMsg.setContent("您送鲜花太频繁了,请稍后再试");
                    channel.writeAndFlush(new TextWebSocketFrame(encoder.encode(responseMsg)));
                    return;
                }
            }

            // 正常送花操作
            for (Channel client : onlineUsers) {
                IMMessage responseMsg = new IMMessage();

                responseMsg.setCmd(IMP.FLOWER.getName());

                if (channel == client) {
                    responseMsg.setSender("you");
                    responseMsg.setContent("你给大家送了一波鲜花");
                    channel.attr(ATTRS).getAndSet(String.valueOf(currentTime));
                } else {
                    responseMsg.setSender(channel.attr(NICK_NAME).get());
                    responseMsg.setTime(currentTime);
                    responseMsg.setContent(channel.attr(NICK_NAME).get() + "送给大家一波鲜花");
                }

                client.writeAndFlush(new TextWebSocketFrame(encoder.encode(responseMsg)));
            }
        }
    }

    public void process(Channel channel, String msg) {
        IMMessage requestMsg = decoder.decode(msg);
        process(channel, requestMsg);

    }


}
