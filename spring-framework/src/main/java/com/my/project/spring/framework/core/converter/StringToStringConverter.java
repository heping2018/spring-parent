package com.my.project.spring.framework.core.converter;

import com.my.project.spring.framework.context.support.GenericConverter;

import java.util.Set;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 15:33
 **/
public class StringToStringConverter implements GenericConverter {
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public String convert(Object source, Class sourceType, Class targetType) {
        if((source == String.class) && targetType == String.class){
            return (String) source;
        }
        throw new IllegalArgumentException("");
    }
}
