package com.lunzi.camry.design.ListenPattern;

import org.springframework.context.ApplicationListener;

/**
 * 自定义的事件监听接口，实现事件监听的功能
 * Created by lunzi on 2019/3/6 11:05 PM
 */
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        myApplicationEvent.print();
    }
}
