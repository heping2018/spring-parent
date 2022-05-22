package com.my.project.spring.framework.beans.support;

import com.my.project.spring.framework.core.factory.config.PCBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


/**
 * @author 86187
 * @description 加载bean配置文件
 * @create 2022/5/21 10:22
 **/
public class PCBeanfinitionReader {

    private List<String> registyBeanClasses = new ArrayList<>();

    private Properties config = new Properties();

    private final String SCAN_PACKAGE = "scanPackage";

    public PCBeanfinitionReader(String ... locations){
        InputStream is = this.getClass().getResourceAsStream(locations[0].replace("classpath:",""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String property) {
        URL url = this.getClass().getClassLoader().getResource(property.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());

        for (File file : classPath.listFiles()){
            if(file.isDirectory()){
                doScanner(SCAN_PACKAGE + "." + file.getName());
            }else{
                if(!file.getName().endsWith(".class")){
                    continue;
                }
                String className = property + "." + file.getName().replace(".class","");
                registyBeanClasses.add(className);
            }
        }
    }


    public List<PCBeanDefinition> loalBeanDefinitions(){
        List<PCBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registyBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if(beanClass.isInterface()){
                    continue;
                }
                result.add(doLoadBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),beanClass.getName()));
                Class<?>[] inerfaces = beanClass.getInterfaces();
                Arrays.stream(inerfaces).forEach(inface -> {
                    result.add(doLoadBeanDefinition(inface.getName(),beanClass.getName()));
                });
            }
        }catch (Exception e){

        }
        return result;
    }

    private PCBeanDefinition doLoadBeanDefinition(String toLowerFirstCase, String name) {
        PCBeanDefinition pcBeanDefinition = new PCBeanDefinition();
        pcBeanDefinition.setBeanClassNanme(name);
        pcBeanDefinition.setFactoryName(toLowerFirstCase);
        return pcBeanDefinition;
    }


    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return config;
    }


}
