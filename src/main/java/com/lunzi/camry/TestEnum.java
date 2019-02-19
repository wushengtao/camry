package com.lunzi.camry;

/**
 * Created by lunzi on 2018/12/13 8:15 PM
 */
public enum TestEnum {
    Error,
    SUCCESS;
    TestEnum(){

    }

    public static void main(String[] args) {
        System.out.println(TestEnum.SUCCESS.ordinal());
    }
}

