package com.lunzi.camry.clzLoader;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import sun.misc.Launcher;

/**
 * Created by lunzi on 2018/7/15 下午5:02
 */
public class TestClassLoader {
    public static void main(String[] args) {
        //System.out.println(System.getProperty("sun.boot.class.path"));
        //System.out.println(System.getProperty("java.ext.dirs"));
       // print(System.getProperty("java.class.path"));
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
    public static void print(String str){
        for(String s:str.split(":")){
            System.out.println(s);
        }

    }

}
