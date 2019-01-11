package com.ke;

import java.io.IOException;

/**
 * 描述: 客户端
 *
 * @author MojiangHua
 * @create 2019-01-11 下午 12:49
 */
public class UserClient {

    public static void main(String[] args) throws IOException {
        User user = new User_Stub();

        int age = user.getAge();

        System.out.println("用户年龄是-->" + age);
    }
}
