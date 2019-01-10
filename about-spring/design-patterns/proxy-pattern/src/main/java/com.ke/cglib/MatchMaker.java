package com.ke.cglib;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MatchMaker implements MethodInterceptor {

    public Object getInstance(Object obj) {
        System.out.println("被代理对象前: " + obj.getClass());
        Enhancer enhancer = new Enhancer();
        // 设置父类对象, 告诉 CGLib 子类应该继承的父类是什么
        enhancer.setSuperclass(obj.getClass());

        // 设置回调
        enhancer.setCallback(this);
        return enhancer.create();
    }


    /**
     * 实际上也是做了字节码重组的事， 但对用户来说是无感知的
     * object 对象是 CGLib 帮我们新建出来的, object 是我们上面设置的父类( enhancer.setSuperclass(obj.getClass());)的子类
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        methodProxy.invokeSuper(object, args);
        System.out.println("正在筛选和你相符合的女性···，如有合适,将马上通知您");
        return null;
    }
}
