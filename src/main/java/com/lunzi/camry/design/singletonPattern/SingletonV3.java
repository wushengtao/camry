package com.lunzi.camry.design.singletonPattern;

/**
 * 单利模式
 * Created by lunzi on 2019/3/5 11:16 PM
 */
public class SingletonV3 {
    private static SingletonV3 instancce=null;//写在static 前面
    static
    {
        instancce=new SingletonV3();
    }

    public static SingletonV3 getInstance(){
        return instancce;
    }

    public static void main(String[] args) {
        System.out.println(SingletonV3.getInstance());

    }
}
