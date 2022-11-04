package com.example.springmanual.framework.aop.advice;


import com.example.springmanual.framework.aop.method.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 15:11
 */
public class BeforeAdviceIntercept implements MethodInterceptor, BeforeAdvice {
    private Object target;
    private Method method;

    public BeforeAdviceIntercept(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object before() throws Throwable {
        return method.invoke(target, null);
    }

    public Object invoke(MethodInvocation m1) throws Throwable {
        this.before();
        return m1.proceed();
    }
}
