package com.ke.aboutrabbitmq.consumerLimiter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.120.100");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String queueName = "test_limit_queue_name";
        String exchangeName = "test_limit_name";
        String rountingKey = "limit.#";
        String exchangeType = "topic";

        // 建立交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 建立queue
        channel.queueDeclare(queueName, false, false, false,null);
        // 建立绑定关系
        channel.queueBind(queueName, exchangeName, rountingKey);

        // 规定一次只能推送一条消息过来，等consumer ack 之后才能再推消息
        channel.basicQos(0, 1, false);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.err.println(" Consumer Received :" + message);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 签收，返回ack
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        // 注意要把 autoack 设置为false
        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> { });
    }
}
