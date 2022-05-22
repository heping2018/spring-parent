package com.my.project.spring.aop.framework;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:53
 **/
public class DefaultProxyFactory extends AbstractProxyFactory {

    private AopProxyFactory aopProxyFactory;
    @Override
    protected AopProxy createAopProxy(ProxyConfig config) {
        return aopProxyFactory.createAopProxy(config);
    }

    @Override
    public Object getProxy(ProxyConfig config) {
        return aopProxyFactory.createAopProxy(config);
    }
}
