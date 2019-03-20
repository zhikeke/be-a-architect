package com.ke.aboutrabbitmq.topicExchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer4TopicExchange {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_topic_exchange_name";
        String exchangeType = "topic";
        String queueName = "test_queue_name";
//        String rountingKey = "user.#";
        String rountingKey = "user.*";

        // 建立交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 建立queue
        channel.queueDeclare(queueName, false, false, false,null);
        // 建立绑定关系
        channel.queueBind(queueName, exchangeName, rountingKey);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.err.println(" Consumer Received :" + message);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
