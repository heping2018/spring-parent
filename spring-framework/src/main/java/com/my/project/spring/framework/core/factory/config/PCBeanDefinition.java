package com.my.project.spring.framework.core.factory.config;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 10:02
 **/
public class PCBeanDefinition {

    private String beanClassNanme;

    private Boolean lazyInit = false;

    private String factoryName;


    public String getBeanClassNanme() {
        return beanClassNanme;
    }

    public void setBeanClassNanme(String beanClassNanme) {
        this.beanClassNanme = beanClassNanme;
    }

    public Boolean getLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(Boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
    @Override
    public String toString() {
        return "PCBeanDefinition{" +
                "beanClassNanme='" + beanClassNanme + '\'' +
                ", lazyInit=" + lazyInit +
                ", factoryName='" + factoryName + '\'' +
                '}';
    }

}
