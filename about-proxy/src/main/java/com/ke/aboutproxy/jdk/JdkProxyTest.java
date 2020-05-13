package com.ke.aboutproxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class JdkProxyTest {

    public static void main(String[] args) {
        Person person = (Person) new Matchmaker().getInstance(new HMan("科科达", "20", "男"));
        person.findLove();

//         byte[] bytes = ProxyGenerator.generateProxyClass("$proxy0", new Class[]{person.getClass()});
//         FileOutputStream fos = new FileOutputStream(new File("D:\\upload\\proxy0.class"));
//         fos.write(bytes);
    }
}
