package com.ke;

public class TemplateTest {
    public static void main(String[] args) {
        // 冲茶
        BrewTea tea = new BrewTea();
        tea.create();

        System.out.println("================================");

        // 冲咖啡
        BrewCoffee coffee = new BrewCoffee();
        coffee.create();
    }
}
