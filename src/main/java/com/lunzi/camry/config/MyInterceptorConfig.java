package com.lunzi.camry.config;

import com.lunzi.camry.interceptor.LoginInterceptor;
import com.lunzi.camry.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lunzi on 2018/10/10 上午9:41
 */
@Configuration
public class MyInterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private MyInterceptor myInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/test/login")
                .excludePathPatterns("/test/loginForm");
        super.addInterceptors(registry);
    }
}
