package com.lunzi.camry.easyDubbo;

/**
 * Created by lunzi on 2019/5/22 11:26 AM
 */
public class TestInterrupt {
    private static volatile Boolean flag=false;

    private static void stop(){
        flag=true;
    }
    public static void main(String[] args) throws InterruptedException {
        //启动中的线程
        Thread runThread=new Thread(()->{
            while(!flag){
                try {
                    System.out.println("sleep 1000");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("被中断了");
        });
        runThread.start();
        Thread.sleep(5000);
        //主线程中断
        stop();
    }
}
