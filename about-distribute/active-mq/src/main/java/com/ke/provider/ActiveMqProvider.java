package com.ke.provider;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 简单 activeMq producer  队列形式
 */
public class ActiveMqProvider {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        Connection connection = null;

        try {
            // 创建连接
            connection = connectionFactory.createConnection();
            connection.start();

            /**
             *   通过Connection对象创建Session会话（上下文环境对象），用于接收消息。
             *   参数1：是否启用事务
             *   参数2：签收模式，一般设置为自动签收
             *   Session.AUTO_ACKNOWLEDGE 当客户端从receiver或onMessage成功返回时，Session自动签收客户端的这条消息的收条
             *   Session.CLIENT_ACKNOWLEDGE  客户端通过调用消息(Message)的acknowledge方法签收消息
             *   Session.DUPS_OK_ACKNOWLEDGE Session不必确保对传送消息的签收，这个模式可能会引起消息的重复，
             *     但是降低了Session的开销，所以只有客户端能容忍重复的消息，才可使用
             */
//            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

            // 创建一个名为: provider-queue 的队列（如果队列存在则不创建）
            // destination 表示目的地
            Destination destination = session.createQueue("provider-queue");
            // 创建消息发送者
            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("Hello, activeMq");

            producer.send(message);

            // session设置启用事务，但不加session.commit(), 该消息不会上传到队列中
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
