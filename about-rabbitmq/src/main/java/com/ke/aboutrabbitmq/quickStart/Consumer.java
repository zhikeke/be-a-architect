package com.ke.aboutrabbitmq.quickStart;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * RabbitMQ 消费端
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        //1 获取连接工厂, 配置连接参数
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //2 获取连接
        Connection connection = connectionFactory.newConnection();

        //3 通过connection 获取 channel
        Channel channel = connection.createChannel();

        //4 声明Queue
        String QUEUE_NAME = "test_queue";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.err.println(" Consumer Received :" + message);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
