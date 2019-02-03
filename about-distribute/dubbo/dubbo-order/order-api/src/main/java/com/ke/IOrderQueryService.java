package com.ke;

public interface IOrderQueryService {

    /**
     * 查询订单接口
     * @param orderId 订单id
     * @return
     */
    String query(Long orderId);
}
