package com.lunzi.camry.listen;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/7/23 上午11:47
 */
@Component
public class MyListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof MyEvent){
            System.out.println("this is myListener");
            MyEvent myEvent=(MyEvent)applicationEvent;
            myEvent.print();
        }
    }
}
