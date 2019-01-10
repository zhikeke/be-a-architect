package com.ke.service;

import com.ke.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean add(User user) {
        System.out.println("开始添加用户");
        return true;
    }

    public boolean remove(Long userId) {
        System.out.println("开始删除用户");
        return true;
    }

    public boolean update(User user) throws Exception{
        System.out.println("开始更新用户");
//        return true;
        throw new RuntimeException("更新用户失败");
    }

    public User query(Long userId) {
        System.out.println("开始查询用户");
        return null;
    }

}
