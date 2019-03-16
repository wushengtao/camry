package com.lunzi.camry.config;

import com.lunzi.camry.ao.ExeAO;
import com.lunzi.camry.ao.impl.ExeAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * Created by lunzi on 2019/3/14 12:17 AM
 */
@Component
@Configuration
public class JobConfig implements ApplicationListener<ContextRefreshedEvent> {
    //@Autowired
    ExeAO exeAO;
    JobConfig(){
        System.out.println("jobconfig init");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //exeAO.testAu();
        System.out.println("event happend");
    }


}
