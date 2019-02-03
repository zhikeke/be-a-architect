package com.ke.chat.protocol;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义IM协议的编码器
 */
public class IMEncoder extends MessageToByteEncoder<IMMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IMMessage msg, ByteBuf out)
            throws Exception {
        out.writeBytes(new MessagePack().write(msg));
    }

    public String encode(IMMessage msg) {
        if (null == msg) {
            return "";
        }

        StringBuilder prex = new StringBuilder("[").append(msg.getCmd()).append("]")
                .append("[").append(msg.getTime()).append("]");

        if (IMP.LOGIN.getName().equals(msg.getCmd()) ||
                IMP.CHAT.getName().equals(msg.getCmd()) ||
                IMP.FLOWER.getName().equals(msg.getCmd())) {

            prex.append("[").append(msg.getSender()).append("]");

        } else if (IMP.SYSTEM.getName().equals(msg.getCmd())) {

            prex.append("[").append(msg.getOnline()).append("]");
        }
        if (!(null == msg.getContent() || "".equals(msg.getContent()))) {
            prex.append(" - ").append(msg.getContent());
        }

        return prex.toString();
    }

}
