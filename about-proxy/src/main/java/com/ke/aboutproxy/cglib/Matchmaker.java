package com.ke.aboutproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Matchmaker implements MethodInterceptor {

    public Object getInstance(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("===========  CGLib 动态代理开始 ==================");
        System.out.println("当前调用方法: " + method.getName());

        methodProxy.invokeSuper(o, objects);

        System.err.println("===========  CGLib 动态代理接收 ==================");
        return null;
    }
}
