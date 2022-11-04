package com.example.springmanual.annotation;

import java.lang.annotation.*;

/**
 * @Author:ZhangYuKun
 * @Date:2022/10/31 17:24
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    String value() default "";
}
