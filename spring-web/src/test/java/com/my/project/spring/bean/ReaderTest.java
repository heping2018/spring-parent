package com.my.project.spring.bean;

import com.my.project.spring.framework.beans.support.PCBeanfinitionReader;
import com.my.project.spring.framework.core.factory.config.PCBeanDefinition;
import org.junit.Test;

import java.util.List;

/**`
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 10:34
 **/
public class ReaderTest {


    @Test
    public void doBeanfinitionReaderTest(){
        PCBeanfinitionReader pcBeanfinitionReader = new PCBeanfinitionReader("/bean.properties");

    }


    @Test
    public void doloalBeanDefinitionsTest(){
        PCBeanfinitionReader pcBeanfinitionReader = new PCBeanfinitionReader("/bean.properties");
        List<PCBeanDefinition> pcs = pcBeanfinitionReader.loalBeanDefinitions();
        pcs.stream().forEach(s -> System.out.println(s));
    }
}
