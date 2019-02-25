package com.lunzi.camry.design.futurePattern;


import java.util.concurrent.*;

/**
 * Created by lunzi on 2019/2/24 4:20 PM
 */
public class FuturePattern {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        FutureClient futureClient=new FutureClient();
//        FutureRst futureRst=futureClient.request(1);
//        System.out.println(futureRst.getData());
        ExecutorService es= Executors.newFixedThreadPool(1);
        FutureTask future=new FutureTask<>(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return 2;
        });
        es.submit(future);
        System.out.println(future.get());

    }

}
class FutureClient{
    private final FutureRst result=new FutureRst();
    public <T> FutureRst<T> request(T requestPara) {
        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            result.setData(requestPara);
        }).start();
        return result;
    }
}
class FutureRst<T>{
    private volatile T data;
    private volatile boolean isFinish;
    public synchronized void setData(T data){
        this.data=data;
        isFinish=true;
        //唤醒线程
        notify();
    }
    public synchronized T getData(){
        while(!isFinish){
            //等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
