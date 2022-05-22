package com.my.project.spring.aop.framework;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 20:02
 **/
public class CglibAopProxy implements AopProxy{

    private ProxyConfig proxyConfig;

    @Override
    public Object getProxy() {
        return ;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
