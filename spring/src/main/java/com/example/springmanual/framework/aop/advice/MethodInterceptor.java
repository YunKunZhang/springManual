package com.example.springmanual.framework.aop.advice;

import com.example.springmanual.framework.aop.method.MethodInvocation;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 14:24
 */
public interface MethodInterceptor extends Advice {

    Object invoke(MethodInvocation var1) throws Throwable;
}
