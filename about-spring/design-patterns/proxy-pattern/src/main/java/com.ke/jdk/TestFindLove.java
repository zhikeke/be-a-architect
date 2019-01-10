package com.ke.jdk;

import com.ke.jdk.custom.KeMatchMaker;

/**
 * 原理:
 *   1. 拿到被代理对象的引用,然后获取它的接口
 *   2. jdk 代理重新生成一个类，同时实现被代理对象的接口
 *   3. 把被代理对象的引用拿到
 *   4. 重新动态生成一个class字节码
 *   5. 编译
 */
public class TestFindLove {
    public static void main(String[] args) throws Exception{
//        Persion obj = (Persion) new MatchMaker().getInstance(new ZhangSan());
//        System.out.println("被代理对象后: " + obj.getClass());
//        obj.findLove();


        // 自定义动态代理
        Persion obj = (Persion) new KeMatchMaker().getInstance(new ZhangSan());
        System.out.println("被代理对象后: " + obj.getClass());
        obj.findLove();
    }
}
