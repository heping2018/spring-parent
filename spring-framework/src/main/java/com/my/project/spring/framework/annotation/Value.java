package com.my.project.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 15:58
 **/


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value() default "";
}
