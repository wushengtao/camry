package com.lunzi.camry;

/**
 * Created by lunzi on 2018/6/18 下午12:44
 */
public class TestException {
    public static void fun(){
        int m=1;
        try {
            int n=m/0;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void main(String args[]){
        int [] a=null;
        System.out.println(a.length);
    }
}
