package com.lunzi.camry.design.listen;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by lunzi on 2018/6/20 上午10:09
 */
public class EventSource{
    private ActionListener actionListener;
    public void addListener(ActionListener actionListener){
        this.actionListener=actionListener;
    }
    public void happened(){
        EasyEvent easyEvent=new EasyEvent();
        easyEvent.print();
        actionListener.action(easyEvent);
    }
    public static void main(String args[]){
        EventSource eventSource=new EventSource();
        eventSource.addListener(new ActionListener() {
            @Override
            public void action(EasyEvent e) {
                System.out.println("触发事件后调用的行为");
            }

            @Override
            public void handle() {
                System.out.println("这是一个回调的方法");
            }
        });
        eventSource.happened();
    }


}
