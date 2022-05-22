package com.my.project.spring.framework.core.converter;

import com.my.project.spring.framework.context.support.GenericConverter;

import java.util.Set;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 15:36
 **/
public class StringToIntegerConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public Integer convert(Object source, Class sourceType, Class targetType) {
        if((source == String.class) && targetType == Integer.class){
            return (Integer) source;
        }
        if((source == String.class) && targetType == int.class){
            return Integer.valueOf((String) source);
        }
        throw new IllegalArgumentException("");
    }
}
