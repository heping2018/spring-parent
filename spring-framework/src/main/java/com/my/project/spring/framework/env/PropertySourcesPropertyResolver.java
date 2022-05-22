package com.my.project.spring.framework.env;


import com.my.project.spring.framework.context.support.GenericConverter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 14:23
 **/
public class PropertySourcesPropertyResolver {


    public final MutablePropertySources propertySources;

    public PropertyPlaceholderHelper propertyPlaceholderHelper;

    private Map<Converterkey,GenericConverter> genericConverterMap = new ConcurrentHashMap<>();

    public PropertySourcesPropertyResolver(MutablePropertySources propertySources) {
        this.propertySources = propertySources;
        this.propertyPlaceholderHelper = new PropertyPlaceholderHelper();

    }
    public String resolveRequiredPlaceholders(String text){
        return propertyPlaceholderHelper.replacePlacehoders(text,new PlaceholderResolver());
    }


    public  <T> T convertValueIfNecessary(Object value, Class<T> targetValueType) {
        if(targetValueType == null){
            return (T) value;
        }
        GenericConverter genericConverter = genericConverterMap
                .get(new Converterkey(value.getClass().getName(),targetValueType.getName()));
        if(genericConverter == null){
        }
        value = genericConverter.convert(value,value.getClass(),targetValueType);
        return (T) value;
    }

    class Converterkey{
        private String orginClass;

        private String targetClass;

        public Converterkey(String orginClass, String targetClass) {
            this.orginClass = orginClass;
            this.targetClass = targetClass;
        }
    }

    class PlaceholderResolver implements PropertyPlaceholderHelper.PlaceholderResolver{

        @Override
        public String resolvePlaceholder(String key) {
            if(propertySources != null){
                for (MapPropertySource<?> propertySource : propertySources){
                    Object value = propertySource.getProperty(key);
                    if(value != null){
                       String result = convertValueIfNecessary(value,String.class);
                       return result;
                    }
                }
            }
            return null;
        }


    }
}
