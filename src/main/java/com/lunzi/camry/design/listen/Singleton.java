package com.lunzi.camry.design.listen;

/**
 * 自己手写一下单粒模式
 * Created by lunzi on 2018/9/19 下午3:59
 */
public class Singleton {
    private static volatile Singleton instance;
    public  static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
    public static void main(String args[]){
        for(int i=0;i<100;i++){
            Thread thread=new Thread(() -> {
                System.out.println(Singleton.getInstance());
            });
            thread.start();
        }
    }

}
