package com.lunzi.camry.config;

import com.lunzi.camry.ao.ExeAO;
import com.lunzi.camry.ao.impl.ExeAOImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lunzi on 2019/3/14 12:21 AM
 */
@Configuration
public class MyConfig {
    MyConfig(){
        System.out.println("myConfig init");
    }
    @ConditionalOnMissingBean
    public ExeAO exeAO(){
        System.out.println("create exeAO");
        return new ExeAOImpl();
    }
}
