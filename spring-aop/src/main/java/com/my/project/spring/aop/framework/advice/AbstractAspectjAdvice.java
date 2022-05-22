package com.my.project.spring.aop.framework.advice;

import com.my.project.spring.aop.framework.aspect.JoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 21:32
 **/
public abstract class AbstractAspectjAdvice implements Advice {
    private Method aspectMethod;

    private Object aspectTarget;

    public AbstractAspectjAdvice(Method aspectMethod,Object aspectTarget){
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }

    public Object invokeAdviceMethod(JoinPoint joinPoint,Object returnvalue,Throwable ex)
            throws InvocationTargetException, IllegalAccessException {
        Class<?>[] paramsTypes = this.aspectMethod.getExceptionTypes();
        if(paramsTypes == null || paramsTypes.length ==0){
            return this.aspectMethod.invoke(this.aspectTarget);
        }else{

        }
    }


}
