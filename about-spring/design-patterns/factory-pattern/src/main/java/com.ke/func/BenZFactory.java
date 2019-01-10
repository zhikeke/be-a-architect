package com.ke.func;

import com.ke.BenZ;
import com.ke.Car;

public class BenZFactory implements Factory {
    @Override
    public Car createCar() {
        return new BenZ();
    }
}
