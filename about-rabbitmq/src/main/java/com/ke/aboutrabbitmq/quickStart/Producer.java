package com.ke.aboutrabbitmq.quickStart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ 消息生产端
 */
public class Producer {

    public static void main(String[] args) throws Exception{
        //1 获取连接工厂, 配置连接参
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
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        //5 发送消息
        for (int i = 0; i < 5; i++) {
            String msg = "Helloo RabbitMQ!";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.err.println("producer send message: " + msg);
        }

        //6 关闭连接
        channel.close();
        connection.close();
    }
}
