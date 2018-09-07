package com.lunzi.camry.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/9/5 下午7:20
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext=context;
    }

    public <T> T getBean(Class clazz){
        return (T)applicationContext.getBean(clazz);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
