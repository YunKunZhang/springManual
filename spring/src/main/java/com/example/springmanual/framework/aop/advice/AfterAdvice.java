package com.example.springmanual.framework.aop.advice;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 15:14
 */
public interface AfterAdvice extends Advice {
    Object after() throws Throwable;
}
