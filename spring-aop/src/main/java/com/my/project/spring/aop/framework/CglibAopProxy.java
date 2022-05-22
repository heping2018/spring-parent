package com.my.project.spring.aop.framework;

import com.my.project.spring.aop.framework.support.AdvisedSupport;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 20:02
 **/
public class CglibAopProxy implements AopProxy{

    private AdvisedSupport proxyConfig;


    public CglibAopProxy(AdvisedSupport proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
