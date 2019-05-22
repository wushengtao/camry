package com.lunzi.camry.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lunzi on 2018/7/16 下午9:06
 */
public class TestMybatis {
    public static void main(String args[]) throws IOException {
        InputStream inputStream= Resources.getResourceAsStream("");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlsession=sqlSessionFactory.openSession();
    }
}

