package com.lunzi.camry.threadpool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 自定义线程池
 * Created by lunzi on 2018/9/17 下午4:58
 */
public class MyThreadPoolV2 {
    private static final int maxThreadNum=3;
    private static final int coreThreadNum=3;
    private static final int taskThreadNum=10;
    private static final boolean working=true;

    private BlockingQueue<Thread> workThreads = new LinkedBlockingDeque(maxThreadNum);//工作线程
    private BlockingQueue<Runnable> tasks=new LinkedBlockingDeque<>(taskThreadNum);//任务队列
    private void execute(Runnable task){
        if(task==null){
            throw new RuntimeException("npe");
        }
        Integer size=workThreads.size();
        if(size<=maxThreadNum){
            Worker worker=new Worker(task);
            Thread thread=new Thread(worker);
            workThreads.offer(thread);
            thread.start();
        }
        else if(size==taskThreadNum&&size<maxThreadNum){
            Worker worker=new Worker(task);
            Thread thread=new Thread(worker);
            workThreads.offer(thread);
            thread.start();
        }else {
            System.out.println("放弃该任务");
        }
    }


    class Worker implements Runnable{
        private Runnable task;
        Worker(Runnable task) {
            this.task=task;
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
                    e.printStackTrace();
                }

            }

        }
    }
    public static void main(String agrs[]){

    }

}

