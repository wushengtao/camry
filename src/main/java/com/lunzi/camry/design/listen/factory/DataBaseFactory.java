package com.lunzi.camry.design.listen.factory;

/**
 * Created by lunzi on 2018/7/29 下午8:03
 */
public interface DataBaseFactory {
    ISqlDatabase createSqlDataBase();
    INoSqlDatabase createNoSqlDataBase();
}
