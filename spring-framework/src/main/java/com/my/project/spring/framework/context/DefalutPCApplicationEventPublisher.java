package com.my.project.spring.framework.context;

import com.my.project.spring.reflect.util.TypeUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 9:49
 **/
public class DefalutPCApplicationEventPublisher implements PCApplicationEventPublisher{

    private List<PCApplicationListener> listeners;

    private Executor taskExecutor;

    @Override
    public void publishEvent(PCApplicationEvent event) {
        for (PCApplicationListener listener : getListeners(event)){
            if(taskExecutor != null){
                taskExecutor.execute(() -> invokeListener(listener,event));
            }else{
                invokeListener(listener,event);
            }
        }
    }
    private void invokeListener(PCApplicationListener listener, PCApplicationEvent event) {
        listener.onApplicationEvent(event);
    }

    public List<PCApplicationListener> getListeners(PCApplicationEvent event) {
        List<PCApplicationListener> listenersEvent = new ArrayList<>();
        for (PCApplicationListener listener : this.listeners){
            List<Class<?>> clazzes = TypeUtils.resolveTypeArgumenFromSuperInterface(listener.getClass());
            Type eventClass = clazzes.get(0);
            if(eventClass == event.getClass()){
                listenersEvent.add(listener);
            }
        }
        return listenersEvent;
    }

    public void setListeners(List<PCApplicationListener> listeners) {
        this.listeners = listeners;
    }


    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
}
