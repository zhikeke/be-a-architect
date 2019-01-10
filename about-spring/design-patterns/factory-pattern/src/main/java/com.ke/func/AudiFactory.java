package com.ke.func;

import com.ke.Audi;
import com.ke.Car;

public class AudiFactory implements Factory {
    @Override
    public Car createCar() {
        return new Audi();
    }
}
