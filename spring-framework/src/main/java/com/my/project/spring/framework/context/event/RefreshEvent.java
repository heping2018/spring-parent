package com.my.project.spring.framework.context.event;

import com.my.project.spring.framework.context.PCApplicationContext;
import com.my.project.spring.framework.context.PCApplicationEvent;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 11:21
 **/
public class RefreshEvent extends PCApplicationEvent {
    public RefreshEvent(PCApplicationContext source) {
        super(source);
    }
}
