package com.ke.abstr;

import com.ke.*;

public abstract class AbstractFactory {

    protected abstract Car createCar();

    public Car createCar(String carType) {
        if (carType.equals(CarEnum.AUDI.getCarType())) {
            return new AudiFactory().createCar();
        }

        if (carType.equals(CarEnum.BENZ.getCarType())) {
            return new BenZFactory().createCar();
        }

        if (carType.equals(CarEnum.BMW.getCarType())) {
            return new BMWFactory().createCar();
        }

        System.out.println("不好意思，暂时没有工厂可以生产该汽车类型··");

        return null;
    }
}
