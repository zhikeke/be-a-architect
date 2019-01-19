package com.ke;

import java.io.Serializable;

/**
 * 调用订单服务Response
 */
public class OrderServiceResponse implements Serializable{
    /**
     * 结果 code
     */
    private String code;

    /**
     * 服务返回数据
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderServiceResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
