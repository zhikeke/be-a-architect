package com.ke.simple;

import com.ke.Car;
import com.ke.CarEnum;

public class SimpleFactoryTest {
    public static void main(String[] args) {
         SimpleFactory simpleFactory = new SimpleFactory();
         Car car = simpleFactory.createCar(CarEnum.BENZ.getCarType());
         System.out.println(car.getName());
    }
}
