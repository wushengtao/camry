package com.lunzi.camry.jdk8;

/**
 * Created by lunzi on 2019/2/28 11:04 PM
 */
public class TestFunc {
    public static void func(Func func){
        func.work();
    }

    public static void main(String[] args) {
        func(()->{

        });
        Func func=TestFunc::dowork;
        func.work();

    }

    public static int testBizFunc(){
        BizFunc bizFunc= o -> null;
        return 1;
    }

    public static void dowork(){
        //System.out.println("123444");
    }
}
