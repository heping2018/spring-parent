package com.my.project.spring.framework.factory;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 11:19
 **/
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
