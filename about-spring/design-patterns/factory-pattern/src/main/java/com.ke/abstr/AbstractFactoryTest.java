package com.ke.abstr;

import com.ke.CarEnum;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        DefaultFactory defaultFactory = new DefaultFactory();

        // 调用默认生产方法
        System.out.println(defaultFactory.createCar());

        // 自定义生产方法  生产奔驰
        System.out.println(defaultFactory.createCar(CarEnum.BENZ.getCarType()));

    }
}
