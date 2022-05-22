package com.my.project.spring.framework.beans;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 10:07
 **/
public class PCBeanWrapper {

    private Object wrapperInstance;

    private Class<?> wrapperClass;


    public PCBeanWrapper(Object wrapperInstance,Class<?> wrapperClass){
        this.wrapperClass = wrapperClass;
        this.wrapperInstance = wrapperInstance;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Class<?> getWrapperClass() {
        return wrapperClass;
    }

    public void setWrapperClass(Class<?> wrapperClass) {
        this.wrapperClass = wrapperClass;
    }
}
