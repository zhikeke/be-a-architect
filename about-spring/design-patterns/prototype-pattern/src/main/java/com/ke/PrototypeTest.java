package com.ke;

import java.util.Arrays;

/**
 * clone 只能拷贝八大基本数据类型 + String
 * 即： byte, short, int, long, float, double, char, boolean, String
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        concretePrototype.setName("keke");
        concretePrototype.setAge(20);
        concretePrototype.setLists(Arrays.asList("haha"));

        ConcretePrototype copy = (ConcretePrototype) concretePrototype.clone();
        System.out.println("name: " + copy.getName() + " age: " + copy.getAge() + " lists: " + copy.getLists());
//        System.out.println("name is same object: " + (concretePrototype.getName() == copy.getName()));
//        System.out.println("age is same object: " + (concretePrototype.getAge() == copy.getAge()));
//        System.out.println("list is same object: " + (concretePrototype.getLists() == copy.getLists()));

    }

}
