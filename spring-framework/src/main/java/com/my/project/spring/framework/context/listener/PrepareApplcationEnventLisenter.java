package com.my.project.spring.framework.context.listener;

import com.my.project.spring.framework.context.PCApplicationListener;
import com.my.project.spring.framework.context.event.PrepareApplicationEvent;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 10:49
 **/

public class PrepareApplcationEnventLisenter implements PCApplicationListener<PrepareApplicationEvent> {
    @Override
    public void onApplicationEvent(PrepareApplicationEvent event) {

    }
}
