package com.ke.aboutspringboot.initialize;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统初始化器方式二: SpringApplication 初始化完毕后手动添加
 * @Order(加载顺序， 越小越快加载)
 * 1. 继承 ApplicationContextInitializer
 * 2.重构启动类
 *   SpringApplication application = new SpringApplication(AboutSpringbootApplication.class);
 *   application.addInitializers(new SecondInitializer());
 *   application.run(args);
 */
@Order(2)
public class SecondInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();

        Map<String, Object> priroityMap = new HashMap<>();
        priroityMap.put("key2", "value2");

        MapPropertySource mapPropertySource = new MapPropertySource("secondInitializer", priroityMap);

        environment.getPropertySources().addLast(mapPropertySource);

        System.err.println("secondInitializer finish");
    }
}
