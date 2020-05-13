package com.ke.aboutspringboot.initialize;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统初始化器方式三: 定义成环境变量被 DelegatingApplicationContextInitializer 发现注册
 * @Order(加载顺序， 越小越快加载)
 * 1. 继承 ApplicationContextInitializer
 * 2.application.properties 中加入 context.initializer.classes={当前类路径}
 * 使用 application.properties 设置方式 比其它加载方式的都先加载
 */
@Order(3)
public class ThreeInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();

        Map<String, Object> priroityMap = new HashMap<>();
        priroityMap.put("key3", "value3");

        MapPropertySource mapPropertySource = new MapPropertySource("threeInitializer", priroityMap);

        environment.getPropertySources().addLast(mapPropertySource);

        System.err.println("threeInitializer finish");
    }
}
