package com.my.project.spring.aop.framework.aspect;

import java.lang.reflect.Method;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 20:48
 **/
public interface JoinPoint {
    Method getMethod();

    Object[] getArguments();

    Object getThis();

    void setUserAttribute(String key,Object value);

    Object getUserAttribute(String key);
}
