package com.ke.abstr;

import com.ke.Audi;
import com.ke.Car;
import com.ke.func.Factory;

public class AudiFactory extends AbstractFactory {
    @Override
    public Car createCar() {
        return new Audi();
    }
}
