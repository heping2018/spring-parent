package com.my.project.spring.aop;

import com.my.project.spring.aop.framework.aspect.JoinPoint;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 20:47
 **/
public class LogAspect {

    public void before(JoinPoint joinPoint){
        System.out.println("调用方法之前执行" + joinPoint);
    }

    public void after(JoinPoint joinPoint){
        System.out.println("调用之后执行" + joinPoint);
    }

    public void afterReturn(JoinPoint joinPoint){
        System.out.println("调用获得返回值之后执行" + joinPoint);
    }


    public void afterThrow(JoinPoint joinPoint){
        System.out.println("抛出异常之后执行" + joinPoint);
    }
}
