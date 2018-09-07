package com.lunzi.camry;

import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.domain.Student;
import com.lunzi.camry.listen.MyEvent;
import com.lunzi.camry.listen.MyPublisher;
import com.lunzi.camry.spring.SpringContextUtil;
import com.lunzi.camry.spring.TestMethod;
import org.apache.commons.configuration.beanutils.DefaultBeanFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * Created by lunzi on 2018/7/22 下午5:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSpring {

    @Autowired
    private TestMethod testMethod;
    @Test
    public void test(){
        Class clazz=Dic.class;
        ApplicationContext applicationContext=SpringContextUtil.getApplicationContext();
        //获取beanFactory
        DefaultListableBeanFactory defaultListableBeanFactory= (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        //bean注入
        BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition(clazz);
        defaultListableBeanFactory.registerBeanDefinition(clazz.getCanonicalName(),builder.getBeanDefinition());
        Object dic=applicationContext.getBean(clazz);

    }
    @Test
    public void testListen(){
        ApplicationContext applicationContext=SpringContextUtil.getApplicationContext();
        MyPublisher myPublisher= (MyPublisher) applicationContext.getBean("myPublisher");
        myPublisher.publishEvent(new MyEvent("1"));
    }
    @Test
    public void testAop(){
        Class clazz=Student.class;
        ApplicationContext applicationContext=SpringContextUtil.getApplicationContext();
        DefaultListableBeanFactory defaultListableBeanFactory= (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition(clazz);
        defaultListableBeanFactory.registerBeanDefinition("student",builder.getBeanDefinition());

    }
}
