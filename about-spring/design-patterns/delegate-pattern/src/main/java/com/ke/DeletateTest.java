package com.ke;

public class DeletateTest {
    public static void main(String[] args) {
        Manager manager = new Manager(new ExectorA());
        // 从表面看上去事情本身是项目经理做的， 实际是委派给了手下去完成具体任务
        manager.doing();
    }
}
