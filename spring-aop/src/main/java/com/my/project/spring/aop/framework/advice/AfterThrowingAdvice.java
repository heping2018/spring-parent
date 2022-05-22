package com.my.project.spring.aop.framework.advice;

import com.my.project.spring.aop.framework.intercept.MethodInterceptor;
import com.my.project.spring.aop.framework.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 19:42
 **/
public class AfterThrowingAdvice extends AbstractAspectjAdvice implements Advice, MethodInterceptor {

    private MethodInvocation methodInvocation;
    private String throwingName;

    public AfterThrowingAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public void setThrowingName(String throwingName) {
        this.throwingName = throwingName;
    }

    public Object invoke(MethodInvocation mi)throws Throwable{
       try {
           return mi.proceed();
       }catch (Throwable e){
           invokeAdviceMethod(mi,null,e.getCause());
           throw e;
       }
    }
}
