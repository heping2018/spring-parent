package com.my.project.spring.reflect.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 17:06
 **/
public interface TypeUtils {
    static List<Class<?>> resolveTypeArgumenFromSuperInterface(Class type){
        List<Class<?>> arguments = new ArrayList<>();
        Type[] clazzes = type.getGenericInterfaces();
        for (Type clazz : clazzes) {
            arguments = resolveTypeArgumentFromType(clazz);
            if(arguments != null && !arguments.isEmpty()){
                break;
            }
        }
        return arguments;
    }

     static List<Class<?>> resolveTypeArgumentFromType(Type type){
        List<Class<?>> arguments = new ArrayList<>();
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)type;
            if(parameterizedType.getRawType() instanceof  Class){
                Type[] types = parameterizedType.getActualTypeArguments();
                for (Type agrument: types) {
                    Class clazz = asClass(agrument);
                    if(clazz != null){
                        arguments.add(clazz);
                    }
                }
            }
        }
        return arguments;
    }

    static Class<?> asClass(Type agrument) {
        if(agrument instanceof Class){
            return (Class<?>) agrument;
        }
        return null;
    }

    static List<Class<?>> resolveTypeArguments(Class target){
        List<Class<?>> arguments = new ArrayList<>();
        while (target != null){
            arguments = resolveTypeArgumenFromSuperInterface(target);
            if(arguments != null && !arguments.isEmpty()){
                break;
            }
            Class klass = target.getSuperclass();
            arguments = resolveTypeArgumentFromType(klass);
            if(arguments != null && !arguments.isEmpty()){
                break;
            }
            target = klass.getSuperclass();
        }
        return arguments;
    }
}
