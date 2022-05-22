package com.my.project.spring.framework.webmvc;

import java.util.Map;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:59
 **/
public class PCModelAndView {
    private String viewName;

    private Map<String,?> model;

    public PCModelAndView(String viewName){
        this(viewName,null);
    }

    public PCModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}

