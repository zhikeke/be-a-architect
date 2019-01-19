package com.ke.zkClient;

import java.io.Serializable;

/**
 * 客户端
 */
public class ClientCenter implements Serializable{
    /**
     * 客户端id
     */
    private Long clientId;

    /**
     * 客户端名字
     */
    private String clientName;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "ClientCenter{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
