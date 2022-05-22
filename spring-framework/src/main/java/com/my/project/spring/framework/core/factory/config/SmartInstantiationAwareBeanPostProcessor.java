package com.my.project.spring.framework.core.factory.config;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Constructor;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:38
 **/
public interface SmartInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor{

    @Nullable
    default Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName)
            throws RuntimeException {

        return null;
    }
}
