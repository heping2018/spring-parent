package com.my.project.spring.aop;

import com.my.project.spring.aop.framework.AopProxy;
import com.my.project.spring.aop.framework.JDKDynamicAopProxy;
import com.my.project.spring.aop.framework.ProxyConfig;
import com.my.project.spring.aop.framework.support.AdvisedSupport;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 20:36
 **/

public class ProxyTest {


    @Test
    public void patternTest(){
        Pattern p = Pattern.compile("(com.my.project.spring.aop..*)");
        Matcher m = p.matcher("com.my.project.spring.aop.abc.bc");
        boolean b = m.matches();
        System.out.println(b);
    }


    @Test
    public void JdkAopProxy(){
        ProxyConfig proxyConfig = new ProxyConfig();
        proxyConfig.setPonitCut("(com.my.project.spring.aop..*)");

        proxyConfig.setAspectClass("com.my.project.spring.aop.LogAspect");
        proxyConfig.setAspectBefore("before");
        AdvisedSupport advisedSupport = new AdvisedSupport(proxyConfig);
        advisedSupport.setTarget(new Target());
        advisedSupport.setTargetClass(Target.class);
        AopProxy aopProxy = new JDKDynamicAopProxy(advisedSupport);
        TargetInterface target = (TargetInterface) aopProxy.getProxy();
        target.tAOp();
    }
}
