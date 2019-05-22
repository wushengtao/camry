package com.lunzi.camry.mybatis;

import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * mapper 注册中心
 * Created by lunzi on 2019/5/20 3:28 PM
 */
public class EasyMapperRegister {
    //各种初始化好的配置
    private final EasyConfiguration config;
    private final Map<Class<?>, EasyMapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, EasyMapperProxyFactory<?>>();
    //构造函数
    EasyMapperRegister(EasyConfiguration config){
        this.config=config;
    }

    public <T> T getMapper(Class<T> type, EasySqlSession sqlSession) {
        EasyMapperProxyFactory easyMapperProxyFactory=knownMappers.get(type);
        if(easyMapperProxyFactory==null){
            //判空
            throw new RuntimeException("cannot find mapper");
        }
        return (T)easyMapperProxyFactory.newInstance(type,sqlSession);
    }
}
