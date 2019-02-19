package com.lunzi.camry.cglib;

import com.lunzi.camry.aop.RecordLog;

/**
 * Created by lunzi on 2018/10/8 下午2:37
 */
public class UserDao {
    @RecordLog
    public void add(){
        System.out.println("this is add method");
    }
    public void update(){
        System.out.println("this is update method");
    }
}
