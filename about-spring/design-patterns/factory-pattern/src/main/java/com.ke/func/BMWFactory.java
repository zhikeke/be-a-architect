package com.ke.func;

import com.ke.BMW;
import com.ke.Car;

public class BMWFactory implements Factory {
    @Override
    public Car createCar() {
        return new BMW();
    }
}
