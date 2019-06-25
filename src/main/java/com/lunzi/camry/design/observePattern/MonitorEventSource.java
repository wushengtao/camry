package com.lunzi.camry.design.observePattern;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by lunzi on 2019/6/25 9:06 AM
 */
public class MonitorEventSource {
    private List<MonitorListener> monitorListenerList= Lists.newArrayList();

    public void add(MonitorListener monitorListener){
        monitorListenerList.add(monitorListener);
    }

    public void observeAll(){
        for (MonitorListener monitorListener:monitorListenerList){
            monitorListener.action();
        }
    }



}
