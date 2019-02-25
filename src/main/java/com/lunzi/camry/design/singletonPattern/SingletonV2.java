package com.lunzi.camry.design.singletonPattern;

/**
 * 延迟加载
 * Created by lunzi on 2019/2/24 3:54 PM
 */
public class SingletonV2 {
    private SingletonV2(){
        System.out.println("私有初始化方法");
    }
    private static SingletonV2 instance=null;

    public static synchronized SingletonV2 getInstance(){
        if(instance==null){
            instance=new SingletonV2();
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(()->{
                System.out.println(   SingletonV2.getInstance().toString());
            }).start();
        }
    }
}
