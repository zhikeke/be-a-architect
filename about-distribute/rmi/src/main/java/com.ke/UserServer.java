package com.ke;

/**
 * 描述: 用户服务
 *
 * @author MojiangHua
 * @create 2019-01-11 下午 12:43
 */
public class UserServer extends User {

    public static void main(String[] args) {
        UserServer userServer = new UserServer();
        userServer.setAge(18);

        User_Skeleton userSkeleton = new User_Skeleton(userServer);
        userSkeleton.start();
    }
}
