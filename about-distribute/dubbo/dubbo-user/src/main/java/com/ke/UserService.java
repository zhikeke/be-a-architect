package com.ke;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class UserService {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/order-comsumer.xml");

        IOrderService orderService = (IOrderService) context.getBean("orderService");

        OrderServiceRequest request = new OrderServiceRequest();
        request.setUserId("keke");
        request.setGoodsId("1000");
        request.setNums(10L);

        OrderServiceResponse response = orderService.placeOrder(request);

        System.out.println(response.toString());

        System.out.println("====================================");

        for (int i = 0; i < 10; i ++) {
            IOrderQueryService orderQueryService = (IOrderQueryService) context.getBean("orderQueryService");

            System.out.println(orderQueryService.query(1000L));

            System.out.println("====================================");
        }

        System.in.read();
    }
}
