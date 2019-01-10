package com.ke.framework;

import java.io.Serializable;

/**
 * sql排序组件
 */
public class Order implements Serializable {
    /**
     * 所需要排序的参数
     */
    private String propertyName;
    /**
     * 是否为升序
     */
    private boolean ascending;

    public String toString() {
        return propertyName + ' ' + (ascending ? "asc" : "desc");
    }

    /**
     * 默认构造器
     */
    protected Order(String propertyName, boolean ascending) {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    /**
     * 升序排序
     *
     * @param propertyName
     * @return Order
     */
    public static Order asc(String propertyName) {
        return new Order(propertyName, true);
    }

    /**
     * 降序排序
     *
     * @param propertyName
     * @return Order
     */
    public static Order desc(String propertyName) {
        return new Order(propertyName, false);
    }

}
