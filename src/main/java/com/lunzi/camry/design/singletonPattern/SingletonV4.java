package com.lunzi.camry.design.singletonPattern;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * 枚举实现单利模式
 * Created by lunzi on 2019/3/5 11:20 PM
 */
public class SingletonV4 {
    private static SingletonV4 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    enum Singleton{
        INSTANCE;
        private SingletonV4 singletonV4;
        Singleton(){
            singletonV4=new SingletonV4();
        }
        private SingletonV4 getInstance(){
            return singletonV4;
        }
    }

    public static void main(String[] args) {
        Lists.newArrayList(1,2,3).forEach(integer -> new Thread(()->{
            System.out.println(SingletonV4.getInstance().hashCode());
        }).start());
    }
}
