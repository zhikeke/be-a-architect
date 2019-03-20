package com.ke.aboutrabbitmq.directExchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Provider4DirectExchange {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_direct_exchange_name";
        String rountingKey = "test.rounting.key";

        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ for direct exchange";
            channel.basicPublish(exchangeName, rountingKey, null, msg.getBytes());
            System.err.println("producer send : " + msg);
        }

        channel.close();
        connection.close();
    }
}
