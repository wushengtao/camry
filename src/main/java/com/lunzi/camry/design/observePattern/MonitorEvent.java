package com.lunzi.camry.design.observePattern;

import java.util.EventObject;

/**
 * Created by lunzi on 2019/6/25 9:22 AM
 */
public class MonitorEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MonitorEvent(Object source) {
        super(source);
    }

    public void monitor(){

    }
}
