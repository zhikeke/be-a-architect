package com.ke.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {

    public static final String EDP = "execution(* com.ke.service..*(..))";

    /**
     * 切面的前置方法 即方法执行前拦截到的方法
     * 在目标方法执行之前的通知
     * @param joinPoint
     */
    @Before(EDP)
    public void before(JoinPoint joinPoint) {
        System.out.println("=========执行前置通知==========");
    }

    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 在注解后加入returning
     * @param joinPoint
     */
    @AfterReturning(EDP)
    public void after(JoinPoint joinPoint) {
        System.out.println("===========执行后置通知============");
    }

    /**
     * 最终通知：目标方法调用之后执行的通知（无论目标方法是否出现异常均执行）
     * @param joinPoint
     */
    @After(value=EDP)
    public void doAfter(JoinPoint joinPoint){
        System.out.println("===========执行最终通知============");
    }


    /**
     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
     * @param joinPoint
     * @param exception 异常
     */
    @AfterThrowing(value=EDP , throwing="exception")
    public void doAfterThrowing(JoinPoint joinPoint,Exception exception) {
        System.out.println("===========执行异常通知============");
        System.out.println("===== 异常信息: " + exception.getMessage() + " ======");
    }

}
