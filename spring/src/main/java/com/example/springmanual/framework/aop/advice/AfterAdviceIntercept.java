package com.example.springmanual.framework.aop.advice;


import com.example.springmanual.framework.aop.method.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 15:14
 */
public class AfterAdviceIntercept implements MethodInterceptor, AfterAdvice {

    private Object target;
    private Method method;

    public AfterAdviceIntercept(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public Object after() throws Throwable {
        return method.invoke(target, null);
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        Object res;
        try {
            res = mi.proceed();
        } finally {
            this.after();
        }
        return res;
    }
}
