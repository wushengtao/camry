package com.lunzi.camry.design.ListenPattern;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件 继承boot的事件基类
 * Created by lunzi on 2019/3/6 11:00 PM
 */
public class MyApplicationEvent extends ApplicationEvent {
    //子类初始化会调用父类的构造方法，所以子类没有下面这个构造方法就直接编译报错
    public MyApplicationEvent(Object source) {
        super(source);
    }
    public void print(){
        System.out.println("event happened");
    }

}
