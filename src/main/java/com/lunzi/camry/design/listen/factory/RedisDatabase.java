package com.lunzi.camry.design.listen.factory;

/**
 * Created by lunzi on 2018/7/29 下午8:18
 */
public class RedisDatabase implements INoSqlDatabase{

    @Override
    public void createCon() {
        System.out.println("this is redis");
    }
}
