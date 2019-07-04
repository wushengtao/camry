package com.lunzi.camry.design.observePattern;


/**
 * Created by lunzi on 2019/6/25 9:21 AM
 */
public class MonitorClient {
    public static void main(String[] args) {
        MonitorEventSource monitorEventSource=new MonitorEventSource();
        monitorEventSource.add(() -> System.out.println("执行动作"));
        monitorEventSource.add(()-> System.out.println("执行动作2"));
        monitorEventSource.observeAll();


    }
}
