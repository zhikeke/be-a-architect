package com.ke;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class UserService {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/order-comsumer.xml");

        IOrderServicce orderServicce = (IOrderServicce) context.getBean("orderService");

        OrderServiceRequest request = new OrderServiceRequest();
        request.setUserId("keke");
        request.setGoodsId("1000");
        request.setNums(10L);

        OrderServiceResponse response = orderServicce.placeOrder(request);

        System.out.println(response.toString());

        System.in.read();
    }
}
