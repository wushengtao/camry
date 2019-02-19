package com.lunzi.camry.aop;

import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/10/8 下午4:29
 */
@Component
public class AOPDao {
    @RecordLog
    public void print(){
        System.out.println("this is print Method");
    }
}
