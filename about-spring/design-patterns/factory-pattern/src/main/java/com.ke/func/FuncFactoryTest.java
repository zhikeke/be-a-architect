package com.ke.func;

public class FuncFactoryTest {

    /**
     * 需要用户关心生产的厂商，增加了代码复杂度
     * @param args
     */
    public static void main(String[] args) {
        Factory factory = new AudiFactory();
        System.out.println(factory.createCar().getName());

        factory = new BMWFactory();
        System.out.println(factory.createCar().getName());

    }
}
