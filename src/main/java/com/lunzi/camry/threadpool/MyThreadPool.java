package com.lunzi.camry.threadpool;


import sun.jvm.hotspot.debugger.Page;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 自定义一个线程池
 * Created by lunzi on 2018/8/2 上午9:03
 */
public class MyThreadPool {
    private static int coreThreadNum = 4;//核心线程池
    private static int maxThreadNum = 6;//最大线程池
    private boolean working=true;//打开、关闭线程池

    private BlockingQueue<Thread> workThreads = new LinkedBlockingDeque(maxThreadNum);//工作线程
    private BlockingQueue<Runnable> tasks=new LinkedBlockingDeque<>(10);//任务队列
    //执行任务
    public void excute(Runnable task){
        if(task==null){
            throw new RuntimeException("npe");
        }
        //核心线程池没有满
        int size=workThreads.size();
        if(size<=coreThreadNum){
            Worker worker=new Worker(task);
            Thread thread=new Thread(worker);
            workThreads.offer(thread);
            thread.start();
        }
        else if(tasks.size()<10){
            tasks.offer(task);
        }
        else if(size<maxThreadNum&&tasks.size()==10){
            Worker worker=new Worker();
            Thread thread=new Thread(worker);
            System.out.println("开启新线程了"+thread.getName());
            workThreads.offer(thread);
            thread.start();
        }
        else {
            System.out.println("放弃该任务");
            return ;
        }
    }
    private class Worker implements Runnable{
        private Runnable task;
        Worker(Runnable task){
            this.task=task;
        }
        Worker(){

        }

        @Override
        public void run() {
            if(task!=null){
                task.run();
            }
            while(working){
                try {
                    Runnable task=tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    System.out.println("终止了");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public void shutDown(){
        this.working=false;
        for(Thread worker:workThreads){
            System.out.println("终止线程名："+worker.getName());
            worker.interrupt();
        }
        System.out.println("终止线程池线程");
        Thread.currentThread().interrupt();
    }
    public static void main(String args[]) throws InterruptedException {
        MyThreadPool myThreadPool=new MyThreadPool();
        for(int i=0;i<100;i++){
            myThreadPool.excute(new Task("任务"+i));
        }
        TimeUnit.SECONDS.sleep(4);
        myThreadPool.shutDown();

    }

}
class Task implements Runnable{
    private String name;
    Task(String name){
        this.name=name;
    }
    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName()+name);
    }

}


