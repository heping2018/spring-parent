package com.my.project.spring.framework.env;

import java.util.Map;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 11:25
 **/
public class MapPropertySource<T> {
    private String name;

    private Map<String, T>  source;


    public MapPropertySource(String name, Map<String, T> source) {
        this.name = name;
        this.source = source;
    }


    public T getProperty(String name){
        return source.get(name);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
