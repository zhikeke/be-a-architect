package com.ke;

/**
 * 订单服务实现类
 */
public class OrderService implements IOrderServicce{

    public OrderServiceResponse placeOrder(OrderServiceRequest orderServiceRequest) {
        System.out.println("-------------请求参数-----------");
        System.out.println(orderServiceRequest.toString());

        OrderServiceResponse response = new OrderServiceResponse();
        response.setCode("200");

        return response;
    }
}
