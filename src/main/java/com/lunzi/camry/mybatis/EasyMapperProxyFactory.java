package com.lunzi.camry.mybatis;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * mapper 代理工厂
 * Created by lunzi on 2019/5/20 3:44 PM
 */
public class EasyMapperProxyFactory<T> {
    private SqlSession sqlSession;
    private Class<T> mapperInterface;
    public <T> T newInstance(Class<T> type, EasySqlSession sqlSession) {
        final EasyMapperProxy easyMapperProxy=new EasyMapperProxy(sqlSession,mapperInterface);
        return newInstance(easyMapperProxy);
    }

    private <T> T newInstance(EasyMapperProxy easyMapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},easyMapperProxy);
    }
}
