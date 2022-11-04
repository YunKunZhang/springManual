package com.example.springmanual.framework.aop.advice;


import com.example.springmanual.framework.aop.method.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/4 9:55
 */
public class AroundAdviceIntercept implements MethodInterceptor, AroundAdvice {

    private Object target;
    private Method method;
    private Object args;

    public AroundAdviceIntercept(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object around() throws Throwable {
        return method.invoke(target, args);
    }

    public Object invoke(MethodInvocation var1) throws Throwable {
        this.args = var1;
        return this.around();
    }
}
