package com.ke;

/**
 * 冲咖啡
 */
public class BrewCoffee extends BrewTemplate {
    @Override
    public void addMaterial() {
        System.out.println("放入咖啡");
    }
}
