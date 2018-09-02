package com.lunzi.camry.listen;

import org.springframework.context.ApplicationEvent;

/**
 * Created by lunzi on 2018/7/23 上午11:46
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
    public void print(){
        System.out.println("this is event");
    }
}
