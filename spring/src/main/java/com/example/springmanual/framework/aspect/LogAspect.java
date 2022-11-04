package com.example.springmanual.framework.aspect;


import com.example.springmanual.framework.aop.method.MethodInvocation;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 16:52
 */
public class LogAspect {
    /**
     * 环绕通知.
     *
     * @return obj
     * @throws Throwable exception
     */
    public Object doAround(MethodInvocation mi) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = mi.proceed();
        System.out.println("环绕通知: 退出方法");
        System.out.println("-----------------------");
        return o;
    }

    /**
     * 前置通知.
     */
    public void doBefore() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知.
     *
     * @param result return val
     */
    public void doAfterReturning(String result) {
        System.out.println("后置通知, 返回值: " + result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知.
     */
    public void doAfter() {
        System.out.println("最终通知");
    }

}
