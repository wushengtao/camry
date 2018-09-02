package com.lunzi.camry.design.listen.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/8/20 下午11:52
 */
@Component
public class CommonPublish implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher=applicationEventPublisher;
    }
    public void register(String name){
        System.out.println("用户"+name+"注册");
        publisher.publishEvent(new CommonEvent(name));
    }

}
