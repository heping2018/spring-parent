package com.my.project.spring.framework.webmvc;

import com.my.project.spring.framework.annotation.PCAutowire;
import com.my.project.spring.framework.annotation.PCRequestParam;
import com.my.project.spring.framework.context.support.GenericConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:57
 **/
public class PCHandlerAdapter {

    @PCAutowire
    private GenericConverter genericConverter;

    public boolean supports(Object handler){
        return handler instanceof PCHandlerMapping;
    }

    public PCModelAndView handle(HttpServletRequest request , HttpServletResponse response,Object handler)
            throws Exception{
        PCHandlerMapping handlerMapping = (PCHandlerMapping) handler;

        Map<String,Integer> paramMapping = new HashMap<>();

        Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]){
                if(a instanceof PCRequestParam){
                    String paramName = ((PCRequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramMapping.put(paramName,i);
                    }
                }
            }
        }

        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> type = paramTypes[i];
            if(type == HttpServletResponse.class || type == HttpServletRequest.class){
                paramMapping.put(type.getName(),i);
            }
        }

        Map<String,String[]> reqParamterMap = request.getParameterMap();

        Object[] paramValues = new Object[paramTypes.length];

        for(Map.Entry<String,String[]> param : reqParamterMap.entrySet()){
            String value = Arrays.toString(param.getValue()).replace("\\[\\]","").replace("\\s","");
            if(!paramMapping.containsKey(value)) continue;
            int index = paramMapping.get(param.getKey());
            paramValues[index] = genericConverter.convert(value,value.getClass(),paramTypes[index]);
        }

        if(paramMapping.containsKey(HttpServletRequest.class.getName())){
            int reqIndex = paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }
        if(paramMapping.containsKey(HttpServletResponse.class.getName())){
            int respIndex = paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }

        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);

        if(result == null) {
            return null;
        }

        boolean isModeAndView = handlerMapping.getMethod().getReturnType() == PCModelAndView.class;

        if(isModeAndView){
           return (PCModelAndView) result;
        }
        return null;

    }

}
