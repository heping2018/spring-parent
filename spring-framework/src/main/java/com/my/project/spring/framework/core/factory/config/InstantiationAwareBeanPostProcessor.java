package com.my.project.spring.framework.core.factory.config;

import com.sun.istack.internal.Nullable;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:34
 **/
public interface InstantiationAwareBeanPostProcessor extends PCBeanPostProcessor {

    @Nullable
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws RuntimeException {
        return null;
    }

    @Nullable
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws RuntimeException {
        return true;
    }


}
