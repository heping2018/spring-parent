package com.my.project.spring.aop.framework.support;

import com.my.project.spring.aop.framework.ProxyConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 20:11
 **/
public class AdvisedSupport {

    private Class targetClass;

    private Object target;

    private Pattern ponitCutClassPattern;

    private transient Map<Method, List<Object>> methodCache;

    private ProxyConfig proxyConfig;

    public AdvisedSupport(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }


    public List<Object> getInterceptorAndDynamicInterceptionAdvice(Method method,Class<?> targetClass) throws Exception {
        List<Object> cached = methodCache.get(method);
        if(cached == null){
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());
            cached = methodCache.get(m);
            // 可能还是空的
            methodCache.put(m,cached);
        }
        return cached;
    }


    public boolean pointCutMatch(){
        return ponitCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

    private void parse(){
        String pointCut = proxyConfig.getPonitCut().replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");

        String pointCutForClass = pointCut.substring(0,pointCut.lastIndexOf("\\(") - 4);
        ponitCutClassPattern =
                Pattern.compile("class " + pointCutForClass.substring(pointCutForClass.lastIndexOf(" ") + 1));
        Class aspectClass = Class.forName(proxyConfig.getAspectClass());
        Map<String,Method> aspectMethods = new HashMap<>();
        for (Method m : aspectClass.getMethods()){
            aspectMethods.put(m.getName(),m);
        }
        for (Method m : targetClass.getMethods()){
            String methodString = m.toString();
            if(methodString.contains("throws")){
                methodString = methodString.substring(0,methodString.lastIndexOf("throws")).trim();
            }
            Matcher matcher = ponitCutClassPattern.matcher(methodString);
            if(matcher.matches()){
              List<Object> advices = new LinkedList<>();
              if(!(proxyConfig.getAspectBefore() == null || "".equals(proxyConfig.getAspectBefore().trim()))){
                  advices.add(new )
              }
            }
        }
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Pattern getPonitCutClassPattern() {
        return ponitCutClassPattern;
    }

    public void setPonitCutClassPattern(Pattern ponitCutClassPattern) {
        this.ponitCutClassPattern = ponitCutClassPattern;
    }
}
