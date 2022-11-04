package com.example.springmanual.annotation;

import java.lang.annotation.*;

/**
 * @author ZhangYuKun
 * @date 2022/10/31 17:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {

    String value() default "";
}
