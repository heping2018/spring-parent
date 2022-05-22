package com.my.project.spring.aop.framework.intercept;

import com.my.project.spring.aop.framework.aspect.JoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 20:53
 **/
public class MethodInvocation implements JoinPoint {

    private Object proxy;  //代理对象
    private Method method; //代理目标方法
    private Object target; //代理目标对象
    private Class<?> targetClass;
    private Object[] arguments;
    private List<Object> interceptorAndDynamicMethodMathcers; // 回调方法链

    private Map<String,Object> userAttributes;

    private int cuurentInterceptorIndex = -1;

    public MethodInvocation(Object proxy, Method method, Object target, Class<?> targetClass, Object[] arguments
            , List<Object> interceptorAndDynamicMethodMathcers) {
        this.proxy = proxy;
        this.method = method;
        this.target = target;
        this.targetClass = targetClass;
        this.arguments = arguments;
        this.interceptorAndDynamicMethodMathcers = interceptorAndDynamicMethodMathcers;
    }

    public Object proceed()throws Throwable{
        // interceptorAndDynamicMethodMathcers
        if(this.cuurentInterceptorIndex == interceptorAndDynamicMethodMathcers.size() - 1){
            return this.method.invoke(target,arguments);
        }
        Object interceptorAndDynamicMethodAdvice = interceptorAndDynamicMethodMathcers.get(++cuurentInterceptorIndex);
        if(interceptorAndDynamicMethodAdvice instanceof MethodInterceptor){
            MethodInterceptor mi = (MethodInterceptor) interceptorAndDynamicMethodAdvice;
            return mi.invoke(this);
        }else{
            return proceed();
        }
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public void setUserAttribute(String key, Object value) {
        if(value != null){
            if(userAttributes == null){
                userAttributes = new HashMap<>();
            }
            userAttributes.put(key,value);
        }else{
            if(userAttributes != null){
                this.userAttributes.remove(key);
            }
        }
    }

    @Override
    public Object getUserAttribute(String key) {
        return userAttributes != null ? userAttributes.get(key) : null;
    }
}
