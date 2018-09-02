package com.lunzi.camry.design.listen.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by lunzi on 2018/8/20 下午11:49
 */
public class CommonEvent extends ApplicationEvent {
    private String name;

    public CommonEvent(Object source) {
        super(source);
    }
    public CommonEvent(Object source,String name){
        super(source);
        this.name=name;
    }

}
