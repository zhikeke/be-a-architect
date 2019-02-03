package com.ke;


import org.springframework.stereotype.Service;

/**
 * 订单服务实现类
 */
@Service(value = "orderService")
public class OrderService implements IOrderService{

    public OrderServiceResponse placeOrder(OrderServiceRequest orderServiceRequest) {
        System.out.println("-------------请求参数-----------");
        System.out.println(orderServiceRequest.toString());

        OrderServiceResponse response = new OrderServiceResponse();
        response.setCode("200");

        return response;
    }
}
