package com.ke.aboutspringboot.initialize;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统初始化器方式一: 定义在spring.factories 文件中被 SpringFactoriesLoader 发现注册
 * @Order(加载顺序， 越小越快加载)
 * 1. 继承 ApplicationContextInitializer
 * 2.META-INF/spring.factories 中加入 org.springframework.context.ApplicationContextInitializer = {当前类路径}
 */
@Order(1)
public class FirstInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();

        Map<String, Object> priroityMap = new HashMap<>();
        priroityMap.put("key1", "value1");

        MapPropertySource mapPropertySource = new MapPropertySource("firstInitializer", priroityMap);

        environment.getPropertySources().addLast(mapPropertySource);

        System.err.println("firstInitializer finish");
    }
}
