package com.my.project.spring.aop.framework.advice;

import com.my.project.spring.aop.framework.aspect.JoinPoint;
import com.my.project.spring.aop.framework.intercept.MethodInterceptor;
import com.my.project.spring.aop.framework.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 19:30
 **/
public class MethodBeforeAdvice extends AbstractAspectjAdvice implements Advice, MethodInterceptor {
    private JoinPoint joinPoint;

    public MethodBeforeAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public void before(Method method,Object[] args,Object target)throws Throwable{
        invokeAdviceMethod(this.joinPoint,null,null);
    }

    public Object invoke(MethodInvocation mi)throws Throwable{
        this.joinPoint = mi;
        this.before(mi.getMethod(),mi.getArguments(),mi.getThis());
        return mi.proceed();
    }
}
