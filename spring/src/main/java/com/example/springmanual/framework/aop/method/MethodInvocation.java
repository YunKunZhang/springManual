package com.example.springmanual.framework.aop.method;

import java.lang.reflect.Method;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 15:19
 */
public interface MethodInvocation {

    Object getTarget();

    Method getMethod();

    Object[] getArguments();

    Object proceed() throws Throwable;

}
