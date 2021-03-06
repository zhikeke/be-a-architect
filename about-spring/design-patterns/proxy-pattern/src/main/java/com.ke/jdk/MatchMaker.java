package com.ke.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 媒婆 代理对象
 */
public class MatchMaker implements InvocationHandler{
    private Persion target;

    // 获取被代理人资料
   public Object getInstance(Persion target) {
       this.target = target;
       Class clazz = target.getClass();
       System.out.println("被代理对象前: " + clazz);
       return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
   }

   @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("你的个人资料: 姓名" + target.getName() + " 性别: " + target.getSex() +
//                " 身高: " + target.getHeight());

        this.target.findLove();

        System.out.println("正在筛选和你相符合的女性···，如有合适,将马上通知您");

        return null;
    }
}
