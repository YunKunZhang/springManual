package com.example.springmanual.framework.aop.method;


import com.example.springmanual.framework.aop.advice.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 15:00
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected Object proxy;
    protected Method method;
    protected Object[] args;
    private List<Object> interceptors;
    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Method method, Object[] args, List<Object> interceptors) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
        this.interceptors = interceptors;
    }

    public Object proceed() throws Throwable {
        if (this.currentInterceptorIndex == this.interceptors.size() - 1) {
            return method.invoke(proxy, args);
        }
        MethodInterceptor advice = (MethodInterceptor) interceptors.get(++currentInterceptorIndex);
        return advice.invoke(this);
    }

    public Object getTarget() {
        return proxy;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return args;
    }

}
