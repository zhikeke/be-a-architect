package com.ke;

import org.springframework.stereotype.Service;

@Service(value = "orderQueryService")
public class OrderQueryService implements IOrderQueryService {

    public String query(Long orderId) {
        System.out.println("orderId-->" + orderId);
        return "衣服,被子";
    }
}
