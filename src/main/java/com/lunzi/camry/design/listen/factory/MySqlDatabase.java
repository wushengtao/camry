package com.lunzi.camry.design.listen.factory;

import org.springframework.beans.factory.BeanFactory;

/**
 * Created by lunzi on 2018/7/29 下午8:18
 */
public class MySqlDatabase implements ISqlDatabase{

    @Override
    public void createCon() {
        System.out.println("this is mysql");
    }
}
