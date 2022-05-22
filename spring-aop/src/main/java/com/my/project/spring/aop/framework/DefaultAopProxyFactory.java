package com.my.project.spring.aop.framework;

import com.my.project.spring.aop.framework.support.AdvisedSupport;

import java.lang.reflect.Proxy;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:56
 **/
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(AdvisedSupport config) {
        if (config.getTargetClass() != null) {
            if (config.getTargetClass().isInterface() || Proxy.isProxyClass(config.getTargetClass())) {
                return new JDKDynamicAopProxy(config);
            }
            return new CglibAopProxy(config);
        }
        return new JDKDynamicAopProxy(config);
    }
}
