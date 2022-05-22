package com.my.project.spring.aop.framework;

import com.my.project.spring.aop.framework.support.AdvisedSupport;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:53
 **/
public class DefaultProxyFactory extends AbstractProxyFactory {

    private AopProxyFactory aopProxyFactory;

    public DefaultProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

    @Override
    protected AopProxy createAopProxy(AdvisedSupport config) {
        return aopProxyFactory.createAopProxy(config);
    }

    @Override
    public Object getProxy(AdvisedSupport config) {
        return createAopProxy(config).getProxy();
    }
}
