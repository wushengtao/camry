package com.lunzi.camry.design.ListenPattern;

/**
 * Created by lunzi on 2019/3/6 9:38 AM
 */
public class EventPattern {
    public static void main(String[] args) {
        EventSource eventSource=new EventSource();
        eventSource.addEventListener(new IEventListener() {
            @Override
            public void eventHappen() {
                System.out.println("button event happened");
            }
        });
        eventSource.eventHappent();
    }
}
