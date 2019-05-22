package com.lunzi.camry.mybatis;

import org.apache.ibatis.session.SqlSession;

/**
 * easy sqlSession
 * Created by lunzi on 2019/5/20 3:29 PM
 */
public interface EasySqlSession {
     <T> T getMapper(Class<T> type);
}
