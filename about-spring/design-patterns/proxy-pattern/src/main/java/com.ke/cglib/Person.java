package com.ke.cglib;

public class Person {
    private String name = "张三";
    private String sex = "男";
    private Integer height = 180;
    private String grilfriendDemand = "白富美,身高165以上";

    public void findLove() {
        System.out.println("女朋友要求: " + this.grilfriendDemand);
    }

}
