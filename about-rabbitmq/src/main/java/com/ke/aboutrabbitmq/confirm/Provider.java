package com.ke.aboutrabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        // 制定消息投递模式： 消息确认模式
        channel.confirmSelect();

        String exchangeName = "test_confrim_name";
        String rountingKey = "comfrim.save";

        String msg = "Hello RabbitMQ for confrim";
        channel.basicPublish(exchangeName, rountingKey, null, msg.getBytes());

        // 添加confirm 监听器
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.err.println("----- handleAck -----");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.err.println("----- handleNack -----");
            }
        });
    }
}
