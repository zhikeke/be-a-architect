package com.ke.jdk.custom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler {@link InvocationHandler}
 */
public interface KeInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
