package com.example.springmanual.annotation;

import java.lang.annotation.*;

/**
 * @author ZhangYuKun
 * @date 2022/10/31 17:28
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";
}
