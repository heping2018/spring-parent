package com.my.project.spring.framework.webmvc.servlet;

import com.my.project.spring.framework.annotation.PCController;
import com.my.project.spring.framework.annotation.PCRequestMapping;
import com.my.project.spring.framework.context.PCApplicationContext;
import com.my.project.spring.framework.webmvc.PCHandlerAdapter;
import com.my.project.spring.framework.webmvc.PCHandlerMapping;
import com.my.project.spring.framework.webmvc.PCViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 9:42
 **/
public class DispatcherServlet extends HttpServlet {

    private final String LCATION = "contextConfigLocation";

    private List<PCHandlerMapping> pcHandlerMappings = new ArrayList<>();

    private Map<PCHandlerMapping,PCHandlerAdapter> handlerAdapterMap = new HashMap<>();

     private List<PCViewResolver> viewResolvers = new ArrayList<>();

     private PCApplicationContext applicationContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        applicationContext =  new PCApplicationContext(servletConfig.getInitParameter(LCATION));
        initStrategies(applicationContext);
    }

    protected void initStrategies(PCApplicationContext context) {
        //请求解析
        initMultipartResolver(context);
        //多语言、国际化
        initLocaleResolver(context);
        //主题View层的
        initThemeResolver(context);
        //解析url和Method的关联关系
        initHandlerMappings(context);
        //适配器
        initHandlerAdapters(context);
        ////异常解析
        initHandlerExceptionResolvers(context);
        ////视图转发（根据视图名字匹配到一个具体模板）
        initRequestToViewNameTranslator(context);
        //解析模板中的内容
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    private void initFlashMapManager(PCApplicationContext context) {
    }

    private void initViewResolvers(PCApplicationContext context) {
        String tempateRoot = context.getConfig().getProperty("templateRoot");
        String rootPath = this.getClass().getClassLoader().getResource(tempateRoot).getFile();
        File rootDir = new File(rootPath);
        for (File template : rootDir.listFiles()) {
            viewResolvers.add(new PCViewResolver(template.getName()));
        }
    }

    private void initRequestToViewNameTranslator(PCApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(PCApplicationContext context) {
    }

    private void initHandlerAdapters(PCApplicationContext context) {
        for (PCHandlerMapping handlerMapping : this.pcHandlerMappings){
            this.handlerAdapterMap.put(handlerMapping,new PCHandlerAdapter());
        }
    }

    private void initHandlerMappings(PCApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        //只要是由Cotroller修饰类，里面方法全部找出来
        //而且这个方法上应该要加了RequestMaping注解
        for (String beanName : beanNames) {
            Object controller = null;
            try {
                controller = context.getBean(beanName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Class clazz = controller.getClass();
            if(!clazz.isAnnotationPresent(PCController.class)){ continue;}
            String url = "";
            if(clazz.isAnnotationPresent(PCRequestMapping.class)){
                PCRequestMapping requestMapping = (PCRequestMapping) clazz.getAnnotation(PCRequestMapping.class);
                url = requestMapping.value();
            }
            //扫描Controller下面的所有的方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if(!method.isAnnotationPresent(PCRequestMapping.class)){ continue;}
                PCRequestMapping requestMapping = method.getAnnotation(PCRequestMapping.class);
                String regex = (url + requestMapping.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                pcHandlerMappings.add(new PCHandlerMapping(controller,method,pattern));
            }

        }
    }

    private void initThemeResolver(PCApplicationContext context) {
    }

    private void initLocaleResolver(PCApplicationContext context) {
    }

    private void initMultipartResolver(PCApplicationContext context) {
    }
}
