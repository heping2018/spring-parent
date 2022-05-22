package com.my.project.spring.aop.framework;

import com.my.project.spring.aop.framework.support.AdvisedSupport;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:50
 **/
public abstract class AbstractProxyFactory implements ProxyFactory {

    protected abstract AopProxy createAopProxy(AdvisedSupport config);
}
