package com.my.project.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 9:56
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PCController {
    String value() default "";
}
