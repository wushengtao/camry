package com.lunzi.camry.lock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by lunzi on 2019/5/21 7:55 PM
 */
public class SafeDateFormat {
    static final ThreadLocal<DateFormat> t1=ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    static DateFormat get(){
        return t1.get();
    }

}
