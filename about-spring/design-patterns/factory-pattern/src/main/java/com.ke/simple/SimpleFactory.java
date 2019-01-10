package com.ke.simple;

import com.ke.*;

/**
 * 简单工厂类  专业化管理 标准化
 */
public class SimpleFactory {

    public Car createCar(String carType) {
        if (carType.equals(CarEnum.AUDI.getCarType())) {
            return new Audi();
        }

        if (carType.equals(CarEnum.BENZ.getCarType())) {
            return new BenZ();
        }

        if (carType.equals(CarEnum.BMW.getCarType())) {
            return new BMW();
        }

        System.out.println("不好意思，你所要的产品我们暂时生产不出来··");

        return null;
    }
}
