package com.my.project.spring.framework;

import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 16:11
 **/
public class ServiceLoaderTest {

    @Test
    public void serviceeLoaderTest(){
        ServiceLoader<ApplicationListenerTest> serviceLoader = ServiceLoader.load(ApplicationListenerTest.class,this.getClass().getClassLoader());
        Iterator<ApplicationListenerTest> applicationListenerTestIterator = serviceLoader.iterator();
        while (applicationListenerTestIterator.hasNext()){
            System.out.println(applicationListenerTestIterator.next());
        }
    }
}
