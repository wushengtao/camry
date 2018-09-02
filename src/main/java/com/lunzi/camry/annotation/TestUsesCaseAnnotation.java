package com.lunzi.camry.annotation;

import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试注解
 * Created by lunzi on 2018/5/29 下午8:12
 */
public class TestUsesCaseAnnotation {
    @UserCase(id = 1, desc = "this is method add")
    public static void add() {
    }

    @UserCase(id = 2, desc = "this is method query")
    public static void query() {

    }

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        Collections.addAll(list,1,2,3);
        Method [] methods=TestUsesCaseAnnotation.class.getDeclaredMethods();
        for(Method method:methods){
            UserCase uc=method.getAnnotation(UserCase.class);
            if(uc!=null){
                System.out.println("Found "+uc.id()+",desc is"+uc.desc());
            }
        }
    }
}
