package com.example.springmanual.framework.aop.advice;

/**
 * @Author:ZhangYuKun
 * @Date:2022/11/4 9:55
 */
public interface AroundAdvice extends Advice {

    Object around() throws Throwable;
}
