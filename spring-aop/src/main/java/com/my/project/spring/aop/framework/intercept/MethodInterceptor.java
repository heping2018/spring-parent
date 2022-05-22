package com.my.project.spring.aop.framework.intercept;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 20:51
 **/
public interface MethodInterceptor {
    Object invoke(MethodInvocation methodInvocation);
}
