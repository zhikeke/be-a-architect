package com.ke.aboutrabbitmq.consumerLimiter;

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

        String exchangeName = "test_limit_name";
        String rountingKey = "limit.save";

        String msg = "Hello RabbitMQ for limit";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName, rountingKey, null, msg.getBytes());
        }
    }
}
