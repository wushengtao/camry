package com.lunzi.camry.exe.core.bean.lang;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

/**
 * Created by lunzi on 2018/9/14 上午11:19
 */
public class ClassScaner {
    /**
     * 扫描指定包路径下所有包含指定注解的类
     *
     * @param packageName 包路径
     * @param annotationClass 注解类
     * @return 类集合
     */
    public static Set<Class<?>> scanPackageByAnnotation(String packageName, final Class<? extends Annotation> annotationClass) {
        return null;
    }

    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) throws IOException {
        //获取ClassLoader
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        //packageName转换成.转换成 /
        packageName=packageName.replace(".","/");
        URL url=classLoader.getResource(packageName);
        Enumeration<URL> enumeration=classLoader.getResources(packageName);
        System.out.println("url"+url);
        System.out.println("enum"+enumeration.toString());
        return null;
    }
    public static void main(String args[]) throws IOException {
        ClassScaner.scanPackage("com.lunzi",null);
        String name=null;
        System.out.println(StringUtils.isEmpty(name));
    }
}
