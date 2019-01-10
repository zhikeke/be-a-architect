package com.ke.abstr;

import com.ke.BMW;
import com.ke.Car;

/**
 * 默认工厂方法  生产宝马
 */
public class DefaultFactory extends AbstractFactory {
    BMWFactory defaultFactory = new BMWFactory();

    @Override
    protected Car createCar() {
        return defaultFactory.createCar();
    }
}
