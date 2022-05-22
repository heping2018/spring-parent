package com.my.project.spring.framework.context;

import java.util.EventObject;

/**
 * @author 86187
 * @description <TODO description class purpose>
 * @create 2022/5/21 14:41
 **/
public abstract class PCApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    private final long timestamp;

    public PCApplicationEvent(Object source) {
        super(source);
        this.timestamp =  System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
