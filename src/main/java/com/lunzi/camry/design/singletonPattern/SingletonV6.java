package com.lunzi.camry.design.singletonPattern;

import com.google.common.collect.Lists;

/**
 * 静态内部类实现单利模式
 * Created by lunzi on 2019/3/5 11:33 PM
 */
public class SingletonV6 {
   private static class SingletonHolder{
       private static final SingletonV6 instance=new SingletonV6();
   }
   private SingletonV6(){

   }
   private static final SingletonV6 getInstance(){
       return SingletonHolder.instance;
   }

    public static void main(String[] args) {
        Lists.newArrayList(1,2,3).forEach(integer -> new Thread(()->{
            System.out.println(SingletonV6.getInstance().hashCode());
        }).start());
    }
}
