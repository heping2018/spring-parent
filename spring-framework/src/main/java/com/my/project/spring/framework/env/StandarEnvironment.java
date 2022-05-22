package com.my.project.spring.framework.env;

import java.io.IOException;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 11:25
 **/
public class StandarEnvironment implements Environment {

    private MutablePropertySources mutablePropertySources;

    private PropertySourcesPropertyResolver propertySourcesPropertyResolver;

    private PropertiesPropertySourceLoader propertiesPropertySourceLoader;

    private final static String APPLICATION_NAME = "application.properties";

    public StandarEnvironment(MutablePropertySources mutablePropertySources) {
        this.mutablePropertySources = mutablePropertySources;
        this.propertySourcesPropertyResolver = new PropertySourcesPropertyResolver(mutablePropertySources);
        this.propertiesPropertySourceLoader = new PropertiesPropertySourceLoader();
        propertiesPropertySourceLoader.load(APPLICATION_NAME);
    }

    public StandarEnvironment() {
    }

    public String resolveRequiredPlaceholders(String text){
        return propertySourcesPropertyResolver.resolveRequiredPlaceholders(text);
    }


}
