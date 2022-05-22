package com.my.project.spring.aop.framework;

import com.my.project.spring.aop.framework.support.AdvisedSupport;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 19:47
 **/
public interface ProxyFactory {

    Object getProxy(AdvisedSupport config);

}
