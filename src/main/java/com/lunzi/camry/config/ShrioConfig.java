package com.lunzi.camry.config;

import com.google.common.collect.Maps;
import com.lunzi.camry.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lunzi on 2019/6/24 2:41 PM
 */
@Configuration
public class ShrioConfig {
    @Bean
    UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean(name="securityManager")
    DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    @Bean
    @ConditionalOnClass(value = {SecurityManager.class})
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilters(filtersMap);

        //URI过滤
        Map<String,String> map = Maps.newLinkedHashMap();

        //可过滤的接口路径


        //所有API路径进行校验
        map.put("/test/shiroLogin","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

}
