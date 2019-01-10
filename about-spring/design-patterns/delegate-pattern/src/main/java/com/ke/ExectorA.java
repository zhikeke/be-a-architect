package com.ke;

/**
 * 手下A
 */
public class ExectorA implements IExecutor {
    @Override
    public void doing() {
        System.out.println("我是ExectorA, 任务我收到了，马上处理");
    }
}
