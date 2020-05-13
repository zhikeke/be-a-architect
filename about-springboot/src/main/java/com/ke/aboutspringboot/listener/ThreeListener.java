package com.ke.aboutspringboot.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * 自定义监听器方式三: 定义成环境变量被 DelegatingApplicationListener 发现注册
 * @Order(加载顺序， 越小越快加载)
 * 1. 继承 ApplicationListener
 * 2.application.properties 中加入 context.listener.classes={当前类路径}
 * 使用 application.properties 设置方式 比其它加载方式的都先加载
 */
@Order(3)
public class ThreeListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.err.println("hello threeListener");
    }
}
