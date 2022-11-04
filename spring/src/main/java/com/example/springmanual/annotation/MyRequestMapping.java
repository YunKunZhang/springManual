package com.example.springmanual.annotation;

import java.lang.annotation.*;

/**
 * @author ZhangYuKun
 * @date 2022/10/31 17:26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    String value() default "";
}
