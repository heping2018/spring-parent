package com.my.project.spring.aop.framework;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:51
 **/
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
