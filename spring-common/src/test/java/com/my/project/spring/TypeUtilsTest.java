package com.my.project.spring;

import com.my.project.spring.reflect.util.TypeUtils;
import org.junit.Test;

import java.lang.reflect.Field;


/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 17:07
 **/
public class TypeUtilsTest {


    Facotry<String> facotryInterface;

    @Test
    public void parameterizedTypeTest(){
        Field[] fields = TypeUtilsTest.class.getDeclaredFields();
     //   TypeUtils.resolveTypeArgumentFromType(fields[0].getGenericType());
        TypeUtils.resolveTypeArgumenFromSuperInterface(FacotryInterface.class);
    }
    interface Facotry<T>{

    }

    final class FacotryInterface implements Facotry<TypeUtils>{

    }
}
