package com.ke;

/**
 * 订单服务对外暴露接口
 */
public interface IOrderServicce {

    /**
     * 用户下单
     * @param orderServiceRequest 请求下单接口所需参数 {@link OrderServiceRequest}
     * @return
     */
    OrderServiceResponse placeOrder(OrderServiceRequest orderServiceRequest);
}
