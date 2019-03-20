package com.ke.aboutrabbitmq.returnListener;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * mandatory 为true的情况下，如果找不到exchange 或 找到exchange 但根据rountingkey 找不到对应的队列，则把消息内容返回给ReturnListener
 *
 */
public class Provider {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_return_name";
        String rountingKey = "return.save";
        String rountingErrorKey = "error.save";

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("-----------  handle return ----------");
                System.err.println("replyCode : " + replyCode);
                System.err.println("replyText : " + replyText);
                System.err.println("exchange : " + exchange);
                System.err.println("routingKey : " + routingKey);
                System.err.println("properties : " + properties);
                System.err.println("body : " + new String(body));
            }
        });


        String msg = "Hello RabbitMQ for confrim";
        // mandatory 为false情况下
//        channel.basicPublish(exchangeName, rountingKey, false, false, null, msg.getBytes());

        // mandatory 为false情况下
//        channel.basicPublish(exchangeName, rountingKey, true, false, null, msg.getBytes());
        channel.basicPublish(exchangeName, rountingErrorKey, false, false, null, msg.getBytes());
//        channel.basicPublish(exchangeName, rountingErrorKey, true, false, null, msg.getBytes());

    }
}
