package com.lunzi.camry.threadpool;

import com.google.common.collect.Lists;
import com.lunzi.camry.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalInt;

/**
 * Created by lunzi on 2018/7/23 下午10:26
 */
public class TestQueue {
    final static Object obj=new Object();
    public static class T1 extends Thread{
        public void run(){
            synchronized (obj){
                System.out.println("T1 start!");
                try {
                    System.out.println("T1 wait for object");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 end!");
            }
        }
    }
    public static class T2 extends Thread{
        public void run(){
            synchronized (obj){
                System.out.println("T2 start!");
                obj.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 end!");
            }
        }
    }
    public static void main(String args[]) throws InterruptedException {
//        Thread t1=new T1();
////        Thread t2=new T2();
////        t2.start();
////        t2.join();
////        t1.start();
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        HashMap map=new HashMap();
//        Collections.synchronizedMap(map);

    }
}
