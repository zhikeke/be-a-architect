package com.ke.rpc.core;

import java.io.Serializable;

public class InvokeEntity implements Serializable {

    /**
     * 调用的服务名称
     */
    private String className;

    /**
     * 调用方法名
     */
    private String methodName;

    /**
     * 参数列表
     */
    private Class<?>[] params;

    /**
     * 参数值
     */
    private Object[] values;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public void setParams(Class<?>[] params) {
        this.params = params;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
