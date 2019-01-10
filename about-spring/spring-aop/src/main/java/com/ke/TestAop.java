package com.ke;

import com.ke.model.User;
import com.ke.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {
    public static void main(String[] args) throws Exception {
        ApplicationContext act =  new ClassPathXmlApplicationContext("application.xml");
        UserService userService = (UserService) act.getBean("userService");
        userService.update(new User());

    }
}
