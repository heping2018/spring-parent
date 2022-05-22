package com.my.project.spring.framework.env;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 15:39
 **/
public class PropertiesPropertySourceLoader implements PropertySourceLoader {

    private Properties config = new Properties();
    @Override
    public String[] getFileExtensions() {
        return new String[]{"properties"};
    }

    @Override
    public List<MapPropertySource<?>> load(String name)  {
        InputStream is = null;
        try {
            is = this.getClass().getResourceAsStream(name.replace("classpath:", ""));
            config.load(is);
            return load(config);
        }catch (Exception e){}
        finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    private List<MapPropertySource<?>> load(Properties config) {
        if(config == null){
            return Collections.EMPTY_LIST;
        }
        List<MapPropertySource<?>> mapPropertySources = new ArrayList<>();
        Enumeration enumeration = config.propertyNames();
        while (enumeration.hasMoreElements()){
            Object key = enumeration.nextElement();
            String value = config.getProperty((String) key);
            HashMap map = new HashMap();
            MapPropertySource source = new MapPropertySource((String) key,map);
            mapPropertySources.add(source);
        }
        return mapPropertySources;
    }

}
