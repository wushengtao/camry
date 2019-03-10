package com.lunzi.camry.lock;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by lunzi on 2019/3/8 8:46 PM
 */
public class SyncMethod {
    public synchronized void lockMethod() throws InterruptedException {
        System.out.println("wait....");
        Thread.sleep(5000);
    }
    public synchronized void othedMethod(){
        System.out.println("其他方法");
    }

    public static void main(String[] args) throws InterruptedException {
        SyncMethod method=new SyncMethod();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    method.lockMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for(int i=0;i<200;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    method.othedMethod();
                }
            }).start();
        }
    }
}
