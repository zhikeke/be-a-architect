package com.ke.aboutproxy.cglib;

public class CglibProxyTest {

    public static void main(String[] args) {
        HMan hMan = new HMan("科科达", "20", "男");

        HMan proxyObj = (HMan) new Matchmaker().getInstance(hMan.getClass());
        proxyObj.findLove();
    }

}
