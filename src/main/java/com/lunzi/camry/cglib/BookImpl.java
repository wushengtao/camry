package com.lunzi.camry.cglib;

import com.lunzi.camry.aop.RecordLog;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/10/8 下午4:52
 */
@Component
public class BookImpl implements BookDao{
    @RecordLog
    @Override
    public void addBook() {
        System.out.println("this is addBook");
    }
}
