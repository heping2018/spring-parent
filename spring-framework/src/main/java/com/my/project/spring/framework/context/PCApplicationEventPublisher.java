package com.my.project.spring.framework.context;

import java.util.Collections;
import java.util.List;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:45
 **/

public interface PCApplicationEventPublisher {

    void publishEvent(PCApplicationEvent event);

   default void setListeners(List<PCApplicationListener> listeners){ }

   default  List<PCApplicationListener> getListeners(){
       return Collections.EMPTY_LIST;
   }

   default void addListener(PCApplicationListener listener){
       getListeners().add(listener);
   }

}
