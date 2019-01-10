package com.ke;

/**
 * 定义一个冲泡东西的模板
 */
public abstract class BrewTemplate {

    public void create() {
        // 1. 烧开水
        boilWater();

        // 2. 放入材料
        addMaterial();

        // 3.冲泡
        brew();
    }

    public abstract void addMaterial();

    protected void boilWater() {
        System.out.println("将水烧到100度");
    }

    protected void brew() {
        System.out.println("开始冲泡");
    }


}
