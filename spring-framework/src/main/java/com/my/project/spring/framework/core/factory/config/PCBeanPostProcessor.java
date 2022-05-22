package com.my.project.spring.framework.core.factory.config;

import com.sun.istack.internal.Nullable;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 13:43
 **/
public interface PCBeanPostProcessor {

    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws RuntimeException {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws RuntimeException {
        return bean;
    }
}
