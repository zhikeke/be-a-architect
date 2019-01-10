package com.ke;

/**
 * 冲茶
 */
public class BrewTea extends BrewTemplate {
    @Override
    public void addMaterial() {
        System.out.println("放入茶叶");
    }
}
