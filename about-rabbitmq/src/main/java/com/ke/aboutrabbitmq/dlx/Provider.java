package com.ke.aboutrabbitmq.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_dlx_name";
        String rountingKey = "dlx.save";

        String msg = "Hello RabbitMQ for dlx";

        // 设置消息10 秒钟过期， 模拟死信队列
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("UTF-8")
                .expiration("10000")
                .build();

        channel.basicPublish(exchangeName, rountingKey, basicProperties, msg.getBytes());
    }
}
