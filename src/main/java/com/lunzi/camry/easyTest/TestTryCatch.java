package com.lunzi.camry.easyTest;

/**
 * try catch finally执行顺序
 * Created by lunzi on 2019/3/13 10:52 AM
 */
public class TestTryCatch {
    public void test(){
        try{
            int i=1/0;
            System.out.println("123");
        }catch (Exception e){
            System.out.println("发生了异常");
        }finally {
            System.out.println("这里是finally");
        }
        System.out.println("继续执行");
    }

    public static void main(String[] args) {
        TestTryCatch testTryCatch=new TestTryCatch();
        testTryCatch.test();
    }
}
