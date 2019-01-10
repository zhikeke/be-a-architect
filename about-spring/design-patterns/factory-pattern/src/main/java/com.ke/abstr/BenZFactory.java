package com.ke.abstr;

import com.ke.BenZ;
import com.ke.Car;
import com.ke.func.Factory;

public class BenZFactory extends AbstractFactory {
    @Override
    public Car createCar() {
        return new BenZ();
    }
}
