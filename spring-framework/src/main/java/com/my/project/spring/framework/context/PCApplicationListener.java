package com.my.project.spring.framework.context;

import java.util.EventListener;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:43
 **/

@FunctionalInterface
public interface PCApplicationListener <E extends PCApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);


}
