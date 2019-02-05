package com.ke.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMqConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = null;

        try {
            // 创建连接
            connection = connectionFactory.createConnection();
            connection.start();

            // 创建回话
            Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

            // 创建一个名为: provider-queue 的队列（如果队列存在则不创建）
            // destination 表示目的地
            Destination destination = session.createQueue("provider-queue");
            // 创建消息接收者
            MessageConsumer consumer = session.createConsumer(destination);

            TextMessage message = (TextMessage) consumer.receive();
            message.acknowledge();

            System.out.println("接收到消息--> " + message.getText());

//            session.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
