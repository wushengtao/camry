package com.lunzi.camry.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lunzi on 2019/5/20 3:38 PM
 */
public class EasyConfiguration {
    private EasyMapperRegister easyMapperRegister=new EasyMapperRegister(this);
    public <T> T getMapper(Class<T> type, EasySqlSession easySqlSession) {
        return easyMapperRegister.getMapper(type,easySqlSession);
    }
}
