package com.ke.aboutspringboot.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * 自定义监听器一： 义在spring.factories 文件中被 SpringFactoriesLoader 发现注册
 * @Order(加载顺序， 越小越快加载)
 * 1. 继承 ApplicationListener
 * 2.META-INF/spring.factories 中加入 org.springframework.context.ApplicationListener = {当前类路径}
 */
@Order(1)
public class FirstListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.err.println("hello firstListener");
    }
}
