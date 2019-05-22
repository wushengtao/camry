package com.lunzi.camry.mybatis;

import org.apache.ibatis.session.SqlSession;

/**
 * Created by lunzi on 2019/5/20 3:31 PM
 */
public class EasyDefaultSqlSession implements EasySqlSession{
    private EasyConfiguration easyConfiguration;
    @Override
    public <T> T getMapper(Class<T> type) {
        return easyConfiguration.getMapper(type,this);
    }
}
