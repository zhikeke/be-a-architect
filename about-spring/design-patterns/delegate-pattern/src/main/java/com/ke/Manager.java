package com.ke;

/**
 * 经理  委派任务给手下做
 */
public class Manager implements IExecutor {
    /**
     *  任务具体执行者
     */
    private IExecutor specificExecutor;

    public Manager(IExecutor executor) {
          this.specificExecutor = executor;
    }

    @Override
    public void doing() {
        System.out.println("任务收到，我将委派:" + specificExecutor + " 去完成此任务");
        this.specificExecutor.doing();
    }
}
