package com.lunzi.camry.design.ListenPattern;

/**
 * 事件源头
 * Created by lunzi on 2019/3/6 9:34 AM
 */
public class EventSource {
    private IEventListener eventListener;

    public void addEventListener(IEventListener eventListener){
        this.eventListener=eventListener;
    }

    public void eventHappent(){
        eventListener.eventHappen();
    }

}
