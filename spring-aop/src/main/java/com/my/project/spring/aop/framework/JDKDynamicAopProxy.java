package com.my.project.spring.aop.framework;

import com.my.project.spring.aop.framework.intercept.MethodInvocation;
import com.my.project.spring.aop.framework.support.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 19:47
 **/
public class JDKDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport config;

    public JDKDynamicAopProxy(AdvisedSupport config) {
        this.config = config;
    }

    public void setConfig(AdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.config.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,this.config.getTargetClass().getInterfaces(),this::invoke);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorAndDynamicMethodMathcers = config.getInterceptorAndDynamicInterceptionAdvice(method,this.config.getTargetClass());
        MethodInvocation methodInvocation =
                new MethodInvocation(proxy,method,config.getTarget(),config.getTargetClass(),args,interceptorAndDynamicMethodMathcers);
        return methodInvocation.proceed();
    }
}
