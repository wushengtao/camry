package com.lunzi.camry.design.listen.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/8/20 下午11:57
 */
@Component
public class CommonListener implements ApplicationListener<CommonEvent> {
    @Override
    public void onApplicationEvent(CommonEvent commonEvent) {
        System.out.println("注册积分服务");
    }
}
