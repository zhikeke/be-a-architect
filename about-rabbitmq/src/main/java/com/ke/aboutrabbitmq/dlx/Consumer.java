package com.ke.aboutrabbitmq.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        // 正常队列属性
        String queueName = "test_dlx_queue_name";
        String exchangeName = "test_dlx_name";
        String rountingKey = "dlx.#";
        String exchangeType = "topic";

        // 建立交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dlx-exchange");

        // 建立queue, 将arguments 设置到声明队列上
        channel.queueDeclare(queueName, true, false, false, arguments);
        // 建立绑定关系
        channel.queueBind(queueName, exchangeName, rountingKey);


        // 进行死信队列声明
        channel.exchangeDeclare("dlx-exchange", "topic", true, false, false, null);
        channel.queueDeclare("dlx-queue", true, false, false, null);
        channel.queueBind("dlx-queue", "dlx-exchange", "#");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.err.println(" Consumer Received :" + message);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
