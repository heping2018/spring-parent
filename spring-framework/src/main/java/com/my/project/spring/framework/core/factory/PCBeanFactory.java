package com.my.project.spring.framework.core.factory;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 10:00
 **/
public interface PCBeanFactory {

    Object getBean(String name) throws Exception;

    Object getBean(Class<?> beanClazz) throws Exception;

    <T> T getBean(String name, Class<T> requiedType) throws Exception;
}
