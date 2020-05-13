package com.ke.aboutspringboot.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * 自定义监听器方式二: SpringApplication 初始化完毕后手动添加
 * @Order(加载顺序， 越小越快加载)
 * 1. 继承 ApplicationListener
 * 2.重构启动类
 *   SpringApplication application = new SpringApplication(AboutSpringbootApplication.class);
 *   application.addInitializers(new SecondInitializer());
 *   application.run(args);
 */
@Order(2)
public class SecondListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.err.println("hello secondListener");
    }
}
