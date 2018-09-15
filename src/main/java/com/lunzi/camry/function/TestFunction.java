package com.lunzi.camry.function;

import java.util.function.Function;

/**
 * Created by lunzi on 2018/9/14 下午10:16
 */
public class TestFunction {
    //测试
    public static void main(String args[]){
        TestFunction testFunction=new TestFunction();
        testFunction.test();
    }
    public  void fun(Function<String,String> function){
        int i;
        System.out.println(function.apply("3"));
    }

    public String method(String me){
        return "123";
    }
    public String test(){
        fun(x->{
            return null;
        });
        return "";
    }

}
