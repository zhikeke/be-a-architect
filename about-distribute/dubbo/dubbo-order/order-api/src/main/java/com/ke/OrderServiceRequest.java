package com.ke;

import java.io.Serializable;

/**
 * 请求订单服务所需参数
 */
public class OrderServiceRequest implements Serializable{

    private static final long serialVersionUID = -338154728433754017L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 购买商品数量
     */
    private Long nums;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Long getNums() {
        return nums;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "OrderServiceRequest{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", nums='" + nums + '\'' +
                '}';
    }
}
