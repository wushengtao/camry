package com.lunzi.camry.easyTest;

/**
 * Created by lunzi on 2019/1/27 10:43 PM
 */
public class TestThreadLocal {
    public static ThreadLocal<StringBuilder> threadLocal=new ThreadLocal<>();
    public static void main(String args[]){
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder stringBuilder=threadLocal.get();


                }
            }).start();
        }
    }
}
