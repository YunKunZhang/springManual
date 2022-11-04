package com.example.springmanual.framework.aop.reflect;


import com.example.springmanual.framework.aop.advice.AfterAdviceIntercept;
import com.example.springmanual.framework.aop.advice.AroundAdviceIntercept;
import com.example.springmanual.framework.aop.advice.BeforeAdviceIntercept;
import com.example.springmanual.framework.aop.method.ReflectiveMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 14:23
 */
public class JdkAopProxy implements AopProxy {

    private Object proxy;
    private Object target;
    private Object[] advices;

    public JdkAopProxy(Object target, Object[] advices) {
        this.target = target;
        this.advices = advices;
    }

    public Object getProxy() {
        if (proxy != null) {
            return proxy;
        }
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class[] interfaces = target.getClass().getInterfaces();
        InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                System.out.println("代理方法:" + methodName);
                DynamicAdvisedInterceptor interceptor = new DynamicAdvisedInterceptor(advices);
                return interceptor.intercept(target, method, args);
            }
        };
        proxy = Proxy.newProxyInstance(classLoader, interfaces, handler);
        return proxy;
    }

    private class DynamicAdvisedInterceptor {

        private Object[] advices;

        public DynamicAdvisedInterceptor(Object[] advices) {
            this.advices = advices;
        }

        public Object intercept(Object proxy, Method method, Object[] args) throws Throwable {
            List<Object> chain = getInterceptorsAndDynamicInterceptionAdvice(advices);
            return new ReflectiveMethodInvocation(proxy, method, args, chain).proceed();
        }

        private List<Object> getInterceptorsAndDynamicInterceptionAdvice(Object[] advices) {
            ArrayList<Object> chain = new ArrayList<Object>();
            if (advices[0] != null) {
                chain.add(new AroundAdviceIntercept(advices[5], (Method) advices[0]));
            }
            if (advices[1] != null) {
                //此处的proxy为切面对象
                chain.add(new BeforeAdviceIntercept(advices[5], (Method) advices[1]));
            }
            if (advices[2] != null) {
                chain.add(new AfterAdviceIntercept(advices[5], (Method) advices[2]));
            }
            return chain;
        }
    }
}
