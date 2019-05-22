package com.lunzi.camry.easyTest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lunzi on 2019/1/10 11:29 PM
 */
public class TestCase {
    public static  int j;

    public static void main(String[] args) {
        testDeadLock();
    }
    public static void testCopyOnWriteList(){
        List<String> list=new CopyOnWriteArrayList();
        new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(2000);
                    System.out.println("list的长度为"+list.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(2000);
                    list.add(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void testDeadLock(){
        final Object obj_1 = new Object(), obj_2 = new Object();

        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                synchronized (obj_1) {
                    synchronized (obj_2) {
                        System.out.println("thread t1 done.");
                    }
                }
            }
        };

        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
                synchronized (obj_2) {
                    synchronized (obj_1) {
                        System.out.println("thread t2 done.");
                    }
                }
            }
        };

        t1.start();

    }
}
