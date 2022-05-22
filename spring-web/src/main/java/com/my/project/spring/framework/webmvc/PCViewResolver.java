package com.my.project.spring.framework.webmvc;

import java.io.File;
import java.util.Locale;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 16:03
 **/
public class PCViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;

    private String viewName;

    public PCViewResolver(String tempalteRoot){
        String tempalteRootPath = this.getClass().getClassLoader().getResource(tempalteRoot).getFile();
        this.templateRootDir = new File(tempalteRootPath);
    }

    public PCView resolveVewName(String viewName, Locale locale){
        this.viewName = viewName;
        if(null == viewName || "".equals(viewName.trim())){
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX)? viewName :(viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+","/" ));
        return new PCView(templateFile);
    }

    public String getViewName(){
        return viewName;
    }
}
