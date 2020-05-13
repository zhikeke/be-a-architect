package com.ke.aboutproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Matchmaker implements InvocationHandler {
    private Person target;

    public Object getInstance(Person person) {
        this.target = person;

        Class clazz = target.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("===========  新加一名优质man ==================");
        System.err.println("姓名:" + target.getName());
        System.err.println("性别:" + target.getSex());
        System.err.println("年龄:" + target.getAge());
        System.err.println("若有意者,联系88888888888888");

        method.invoke(target, args);
//        this.target.findLove();

        return null;
    }
}
