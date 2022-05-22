package com.my.project.spring.framework;

import com.my.project.spring.framework.context.DefalutPCApplicationEventPublisher;
import com.my.project.spring.framework.context.PCApplicationEvent;
import com.my.project.spring.framework.context.PCApplicationListener;
import org.junit.Test;

import java.util.ArrayList;


/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/22 10:03
 **/
public class ApplicationListenerTest {

    @Test
    public void listenerTest(){
        PCApplicationEvent applicationEvent = new TestPCApplicationEvent("test",1000);
        PCApplicationListener listener =  new TestPCApplicationListener();
        DefalutPCApplicationEventPublisher defalutPCApplicationEventPublisher = new DefalutPCApplicationEventPublisher();
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(listener);
        defalutPCApplicationEventPublisher.setListeners(arrayList);
        defalutPCApplicationEventPublisher.publishEvent(applicationEvent);
    }

    static class TestPCApplicationEvent extends PCApplicationEvent{
        public TestPCApplicationEvent(Object source, long timestamp) {
            super(source);
        }
    }
    static class    TestPCApplicationListener implements PCApplicationListener<TestPCApplicationEvent>{
        @Override
        public void onApplicationEvent(TestPCApplicationEvent event) {
            System.out.println(event.getTimestamp());
        }
    }

}
