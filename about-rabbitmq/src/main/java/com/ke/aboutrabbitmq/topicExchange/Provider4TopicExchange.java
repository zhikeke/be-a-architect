package com.ke.aboutrabbitmq.topicExchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Provider4TopicExchange {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_topic_exchange_name";
        String USER_SAVE = "user.save";
        String USER_DEL = "user.del";
        String USER_INFO = "user.info.id";

        String msg = "Hello RabbitMQ for direct exchange";
        channel.basicPublish(exchangeName, USER_SAVE, null, msg.getBytes());
        channel.basicPublish(exchangeName, USER_DEL, null, msg.getBytes());
        channel.basicPublish(exchangeName, USER_INFO, null, msg.getBytes());

        channel.close();
        connection.close();
    }
}
