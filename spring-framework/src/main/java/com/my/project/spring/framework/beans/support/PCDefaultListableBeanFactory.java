package com.my.project.spring.framework.beans.support;

import com.my.project.spring.framework.context.PCApplicationContext;
import com.my.project.spring.framework.context.PCApplicationListener;
import com.my.project.spring.framework.context.support.PCAbstractApplicationContext;
import com.my.project.spring.framework.core.factory.config.PCBeanDefinition;
import com.my.project.spring.framework.core.factory.config.PCBeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 10:14
 **/
public class PCDefaultListableBeanFactory extends PCAbstractApplicationContext {

    protected final List<PCBeanPostProcessor> processors = new ArrayList<>();

    protected final Map<String, PCBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    protected final List<String> beanNames = new ArrayList<>();


    protected Object beforeInitialization(Object bean,String beanName){
        Object result = bean;
        for (PCBeanPostProcessor beanPostProcessor : getProcessors()){
            result = beanPostProcessor.postProcessBeforeInitialization(bean,beanName);
        }
        return result;
    }

    protected Object afterInitialization(Object bean,String beanName){
        Object result = bean;
        for (PCBeanPostProcessor beanPostProcessor : getProcessors()){
            result = beanPostProcessor.postProcessAfterInitialization(bean,beanName);
        }
        return result;
    }

    public List<PCBeanPostProcessor> getProcessors(){
        return processors;
    }

    public void addProcessor(PCBeanPostProcessor pcBeanPostProcessor){
        this.processors.add(pcBeanPostProcessor);
    }


}
