package com.example.springmanual.framework.aop.advice;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/3 15:11
 */
public interface BeforeAdvice extends Advice {
    Object before() throws Throwable;
}
