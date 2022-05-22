package com.my.project.spring.aop.framework;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:50
 **/
public abstract class AbstractProxyFactory implements ProxyFactory {

    protected abstract AopProxy createAopProxy(ProxyConfig config);
}
