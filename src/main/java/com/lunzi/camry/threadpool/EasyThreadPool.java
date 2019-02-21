package com.lunzi.camry.threadpool;

import javafx.concurrent.WorkerStateEvent;

import javax.annotation.security.RunAs;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 简单的线程池实现
 * Created by lunzi on 2019/2/20 2:03 PM
 */
public class EasyThreadPool {
    private int corePoolSize;
    private int maxPoolSize;
    private volatile Boolean working=true;
    private BlockingQueue<Thread> workQueue;
    private BlockingQueue<Runnable> taskQueue;

    public EasyThreadPool(int corePoolSize) {
        this.corePoolSize=corePoolSize;
        this.maxPoolSize=corePoolSize;
        this.workQueue=new ArrayBlockingQueue<>(corePoolSize);
        this.taskQueue=new ArrayBlockingQueue<>(6);
    }

    private void execute(Runnable runnable){
        //当前线程队列中的线程数
        int size=workQueue.size();
        if(size<corePoolSize){
            //当前线程数小于core
            //创建新的线程 加入 queue
            Work work=new Work(runnable);
            Thread thread=new Thread(work);
            System.out.println("开启了新的线程"+thread.getId());
            workQueue.offer(thread);
            thread.start();
        }else if(size>=corePoolSize&&taskQueue.size()<=10){
            //放进任务队列中
            taskQueue.offer(runnable);
        }else {
            System.out.println("直接放弃任务了");
        }

    }

    private void shutDown(){
        //关闭线程池
        this.working=false;
        for(Thread thread:workQueue){
            thread.interrupt();
            System.out.println(thread.getName()+"终止线程");
        }
        System.out.println("终止线程池线程");

    }

    class Work implements Runnable{
        private Runnable task;
        public Work(Runnable task){
            this.task=task;
        }
        @Override
        public void run() {
            if(task!=null){
                task.run();
            }
            //一直循环
            while(working&&!Thread.currentThread().isInterrupted()){
                //从taskQueue获取
                try {
                    Runnable runnable=taskQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EasyThreadPool easyThreadPool=new EasyThreadPool(2);
        for(int i=0;i<5;i++){
            easyThreadPool.execute(()->{
                System.out.println("执行任务");
            });
        }
        Thread.sleep(5000);
        easyThreadPool.shutDown();
    }
}
