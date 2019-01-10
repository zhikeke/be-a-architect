package com.ke.abstr;

import com.ke.BMW;
import com.ke.Car;
import com.ke.func.Factory;

public class BMWFactory extends AbstractFactory {
    @Override
    public Car createCar() {
        return new BMW();
    }
}
