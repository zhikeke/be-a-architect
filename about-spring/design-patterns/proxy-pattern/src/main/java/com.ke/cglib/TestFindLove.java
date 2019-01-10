package com.ke.cglib;

public class TestFindLove {
    // JDK 动态代理是通过接口进行强制类型转换
    // 生成以后的代理对象可以强制装换为接口

    // CGLib 的动态代理是通过一个被代理对象的子类，然后重写父类的方法
    // 生成以后的对象可以强制转为被代理对象 (子类引用指向父类对象)


    public static void main(String[] args){
         Person zhangSan = (Person)new MatchMaker().getInstance(new Person());
         System.out.println("被代理对象后: " + zhangSan.getClass());
         zhangSan.findLove();
    }
}
