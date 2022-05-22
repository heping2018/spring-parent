package com.my.project.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 9:58
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PCRequestParam {
    String value() default "";
}
