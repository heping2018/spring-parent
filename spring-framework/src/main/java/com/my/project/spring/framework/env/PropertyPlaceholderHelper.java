package com.my.project.spring.framework.env;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 14:27
 **/
public class PropertyPlaceholderHelper {

    private final String placeholderPrefix = "${";

    private final String placeholderSuffix = "}";

    public String replacePlacehoders(String value,PlaceholderResolver placeholderResolver){
        if(value != null){
            return parseStringValue(value,placeholderResolver);
        }
        throw new IllegalArgumentException("value is Empty");
    }

    private String parseStringValue(String value, PlaceholderResolver placeholderResolver) {
        int index = value.indexOf(placeholderPrefix);
        if(index < 0){
            return value;
        }
        int endIndex = value.indexOf(placeholderSuffix);
        String result = value.substring(index + placeholderSuffix.length(),endIndex);
        String propVal = placeholderResolver.resolvePlaceholder(result);
        if(propVal == null){
            return result;
        }
        return propVal;
    }



    interface  PlaceholderResolver{
        String resolvePlaceholder(String placeholderName);
    }

}
