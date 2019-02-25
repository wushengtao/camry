package com.lunzi.camry.design.singletonPattern;

/**
 * 没有延迟加载的单例模式
 * Created by lunzi on 2019/2/24 3:48 PM
 */
public class SingletonV1 {
    //私有构造函数
    private SingletonV1(){
        System.out.printf("私有初始化方法");
    }
    private static SingletonV1 instance=new SingletonV1();
    public static SingletonV1 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(()->{
                System.out.println( SingletonV1.getInstance().toString());
            }).start();
        }
    }
}
