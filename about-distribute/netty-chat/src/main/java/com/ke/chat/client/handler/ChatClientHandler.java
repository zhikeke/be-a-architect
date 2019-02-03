package com.ke.chat.client.handler;

import com.ke.chat.protocol.IMMessage;
import com.ke.chat.protocol.IMP;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.IOException;
import java.util.Scanner;

import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

/**
 * 聊天客户端逻辑实现
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<IMMessage>{
	
	private static Logger LOG = Logger.getLogger(ChatClientHandler.class);
	private ChannelHandlerContext ctx;
	private String nickName;


	public ChatClientHandler(String nickName){
		this.nickName = nickName;
	}
	
	/**启动客户端控制台*/
    private void session() throws IOException {
    		new Thread(){
    			public void run(){
    				LOG.info(nickName + ",你好，请在控制台输入消息内容");
    				IMMessage message = null;
    		        Scanner scanner = new Scanner(System.in);
    		        do {
                        if(scanner.hasNext()){
                            String input = scanner.nextLine();
                            if("exit".equals(input)){
                                message = new IMMessage(IMP.LOGOUT.getName(), System.currentTimeMillis(), nickName);
                            }else{
                                message = new IMMessage(IMP.CHAT.getName(), System.currentTimeMillis(), nickName, input);
                            }
                        }
    		        } while (sendMsg(message));

    		        scanner.close();
    			}
    		}.start();
    }
	
    /**
     * tcp链路建立成功后调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	this.ctx = ctx;
        IMMessage message = new IMMessage(IMP.LOGIN.getName(),System.currentTimeMillis(),this.nickName);
        sendMsg(message);
        LOG.info("成功连接服务器,已执行登录动作");
        session();
    }

    /**
     * 发送消息
     * @param msg
     * @return
     * @throws IOException 
     */
    private boolean sendMsg(IMMessage msg){
        ctx.channel().writeAndFlush(msg);
        LOG.info("已发送至聊天面板,请继续输入");
        return msg.getCmd().equals(IMP.LOGOUT) ? false : true;
    }
//    /**
//     * 收到消息后调用
//     * @throws IOException
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
//        System.out.println("监听到消息");
//    	IMMessage m = (IMMessage)msg;
//    	LOG.info(m);
//    }

    protected void channelRead0(ChannelHandlerContext ctx, IMMessage msg) throws Exception {
        System.out.println("监听到消息");
    	IMMessage m = (IMMessage)msg;
    	LOG.info(m);
    }

    /**
     * 发生异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	LOG.info("与服务器断开连接:"+cause.getMessage());
        ctx.close();
    }
}
