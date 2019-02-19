package com.lunzi.camry.easyTest;

/**
 * Created by lunzi on 2019/1/10 11:29 PM
 */
public class TestCase {
    public static  int j;

    public static void main(String[] args) {
        new Thread(){
            int i=0;
            @Override
            public void run() {
                while (true){
                    System.out.println("222222");
                    while (j>i){
                        System.out.println(111);
                    }
                }
            }
        }.start();
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        j=1;
        System.out.println(j);
    }
}
