package com.ke;


public class OrderQueryService implements IOrderQueryService {

    public String query(Long orderId) {
        System.out.println("orderId-->" + orderId);
        return "牙刷，牙膏";
    }
}
