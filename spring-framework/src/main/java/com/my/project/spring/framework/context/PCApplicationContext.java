package com.my.project.spring.framework.context;

import com.my.project.spring.aop.framework.DefaultAopProxyFactory;
import com.my.project.spring.aop.framework.DefaultProxyFactory;
import com.my.project.spring.aop.framework.ProxyConfig;
import com.my.project.spring.aop.framework.support.AdvisedSupport;
import com.my.project.spring.framework.annotation.PCAutowire;
import com.my.project.spring.framework.annotation.PCService;
import com.my.project.spring.framework.annotation.Value;
import com.my.project.spring.framework.beans.PCBeanWrapper;
import com.my.project.spring.framework.beans.support.PCBeanfinitionReader;
import com.my.project.spring.framework.beans.support.PCDefaultListableBeanFactory;
import com.my.project.spring.framework.context.event.PrepareApplicationEvent;
import com.my.project.spring.framework.core.factory.PCBeanFactory;
import com.my.project.spring.framework.core.factory.config.PCBeanDefinition;
import com.my.project.spring.framework.core.factory.config.PCBeanPostProcessor;
import com.my.project.spring.framework.env.StandarEnvironment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 10:17
 **/
public class PCApplicationContext extends PCDefaultListableBeanFactory implements PCBeanFactory,PCApplicationEventPublisher {

    private String[] configLocations;

    private PCBeanfinitionReader reader;

    //单例的IOC 容器缓存
    private Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    // 通用缓存
    private Map<String, PCBeanWrapper> factoryBeanInstcanceCache = new ConcurrentHashMap<>();

    private PCApplicationEventPublisher applicationEventPublisher;

    private List<PCApplicationListener> applicationListeners = new ArrayList<>(256);

    // 早期事件
    private final List<PrepareApplicationEvent> earlyEvent =  new ArrayList<>();

    private StandarEnvironment standarEnvironment;

    private DefaultProxyFactory defaultProxyFactory;


    public PCApplicationContext(String... configLocations){
        this.configLocations = configLocations;
        try {
            refresh();
        }catch (Exception e){}
    }

    @Override
    protected void refresh() throws Exception {

        prepareRefresh();
        //1 加载配置文件
        reader = new PCBeanfinitionReader(configLocations);
        List<PCBeanDefinition> beanDefinitions = reader.loalBeanDefinitions();

        //2 注册beanPostProcessor
        registerBeanPostProcessors(beanDefinitions);

        //3 注册 将bean 信息加载到缓存中
        doRegisterBeanDefinition(beanDefinitions);

        //4 加载不是延迟类 初始化
        doAutowirted();

        //5  注册事件监听器
        registerApplicationListener();
    }

    private void registerApplicationListener() {
        applicationEventPublisher.setListeners(this.applicationListeners);
    }

    private void prepareRefresh() {
        this.applicationEventPublisher = new DefalutPCApplicationEventPublisher();
        earlyEvent.add(new PrepareApplicationEvent(this));
        this.setStandarEnvironment(getDefaultEnvironment());
        this.defaultProxyFactory = new DefaultProxyFactory(new DefaultAopProxyFactory());

    }

    public StandarEnvironment getDefaultEnvironment(){
        if(this.standarEnvironment != null){
            return this.standarEnvironment;
        }
        return new StandarEnvironment();
    }


    public void setStandarEnvironment(StandarEnvironment standarEnvironment) {
        this.standarEnvironment = standarEnvironment;
    }

    private void registerBeanPostProcessors(List<PCBeanDefinition> beanDefinitions) throws Exception {
        for (PCBeanDefinition beanDefinition : beanDefinitions){
            String name = beanDefinition.getFactoryName();
            PCBeanPostProcessor pcBeanPostProcessor = getBean(name, PCBeanPostProcessor.class);
            addProcessor(pcBeanPostProcessor);
        }

    }

    private void doAutowirted() {
        for (Map.Entry<String,PCBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getValue().getBeanClassNanme();
            if(!beanDefinitionEntry.getValue().getLazyInit()){
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<PCBeanDefinition> beanDefinitions) throws Exception {
        for (PCBeanDefinition beanDefinition : beanDefinitions){
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryName())){
                throw new Exception("The " + beanDefinition.getBeanClassNanme() + "is  exists");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryName(),beanDefinition);
            super.beanNames.add(beanDefinition.getFactoryName());
        }
    }

    @Override
    public Object getBean(String name) throws Exception {
        PCBeanDefinition beanDefinition = super.beanDefinitionMap.get(name);
        Object instnce = instantiateBean(beanDefinition);
        if(instnce == null){
            return null;
        }
        super.beforeInitialization(instnce,name);
        PCBeanWrapper pcBeanWrapper = new PCBeanWrapper(instnce,beanDefinition.getClass());

        this.factoryBeanInstcanceCache.put(name,pcBeanWrapper);
        parseValueString(name,instnce);
        populateBean(name,instnce);
        super.afterInitialization(instnce,name);

        return this.factoryBeanInstcanceCache.get(name).getWrapperInstance();
    }

    private void populateBean(String name, Object instnce) {
        Class clazz = instnce.getClass();
        if (clazz.isAnnotationPresent(PCService.class)) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields){
            if(!field.isAnnotationPresent(PCAutowire.class)){
                continue;
            }
            if(!field.isSynthetic()){
                continue;
            }
            PCAutowire autowire = field.getAnnotation(PCAutowire.class);

            String autowiredName = autowire.value().trim();
            if("".equals(autowiredName)){
                autowiredName = field.getType().getName();
            }
            try {
                field.set(instnce,this.factoryBeanInstcanceCache.get(autowiredName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseValueString(String name, Object instnce){
        Class clazz = instnce.getClass();
        if (clazz.isAnnotationPresent(PCService.class)) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields){
            if(!field.isAnnotationPresent(Value.class)){
                continue;
            }
            if(!field.isSynthetic()){
                continue;
            }
            Value value = field.getAnnotation(Value.class);
            String autowiredName = value.value().trim();
            if("".equals(autowiredName)){
                autowiredName =  standarEnvironment.resolveRequiredPlaceholders(autowiredName);
            }
            try {
                field.set(instnce,autowiredName);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Object instantiateBean(PCBeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassNanme();
        try {
            if (factoryBeanObjectCache.get(className) != null) {
                instance = factoryBeanObjectCache.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                AdvisedSupport config = instantionAopConfig(beanDefinition);
                if(config.pointCutMatch()){
                    instance = createProxy(config);
                }
                this.factoryBeanObjectCache.put(beanDefinition.getBeanClassNanme(),instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    private Object createProxy(AdvisedSupport config) {
       return defaultProxyFactory.getProxy(config);
    }

    private AdvisedSupport instantionAopConfig(PCBeanDefinition beanDefinition) {
        ProxyConfig config = new ProxyConfig();
        config.setPonitCut(reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(reader.getConfig().getProperty("aspectAfter"));
        config.setAspectAfterThrow(reader.getConfig().getProperty("aspectAfterThrow"));
        return new AdvisedSupport(config);
    }

    @Override
    public Object getBean(Class<?> beanClazz) throws Exception {
        return getBean(beanClazz.getName());
    }

    @Override
    public <T> T getBean(String name, Class<T> requiedType) throws Exception {
        Object obj = getBean(name);
        if(!requiedType.isInstance(obj)){
            throw new RuntimeException("bean is not found");
        }
        return (T) obj;
    }


    public Properties getConfig(){
        return reader.getConfig();
    }

    public int getBeanDEfinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    @Override
    public void publishEvent(PCApplicationEvent event) {
       this.applicationEventPublisher.publishEvent(event);
    }




}
