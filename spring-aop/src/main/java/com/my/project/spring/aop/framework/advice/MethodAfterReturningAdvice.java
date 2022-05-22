package com.my.project.spring.aop.framework.advice;

import com.my.project.spring.aop.framework.aspect.JoinPoint;
import com.my.project.spring.aop.framework.intercept.MethodInterceptor;
import com.my.project.spring.aop.framework.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 19:38
 **/
public class MethodAfterReturningAdvice extends AbstractAspectjAdvice implements Advice, MethodInterceptor {

    private JoinPoint joinPoint;

    public MethodAfterReturningAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public void afterReturning(Object returnValue,Method method,Object[] args,Object target)throws Throwable{
        invokeAdviceMethod(this.joinPoint,returnValue,null);
    }

    public Object invoke(MethodInvocation mi)throws Throwable{
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal,mi.getMethod(),mi.getArguments(),mi.getThis());
        return retVal;
    }
}
