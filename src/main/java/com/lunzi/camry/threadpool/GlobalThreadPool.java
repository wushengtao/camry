package com.lunzi.camry.threadpool;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 全局线程池
 * Created by lunzi on 2018/10/8 下午10:20
 */
public class GlobalThreadPool {
    private static ExecutorService executor;

    private GlobalThreadPool() {

    }

    static {
        init();
    }

    public synchronized static void init() {
        if (null != executor) {
            executor.shutdown();
        }
        executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public synchronized static void shutdown(boolean isNow) {
        if (null != executor) {
            if (isNow) {
                executor.shutdownNow();
            } else {
                executor.shutdown();
            }
        }
    }

    public static ExecutorService getExecutor(){
        return executor;
    }
    public static void execute(Runnable runable){
        try{
            executor.execute(runable);
        }catch (Exception e){
            throw new RuntimeException("task error!");
        }
    }

    public static <T> Future<T> submit(Callable<T> task){
        return executor.submit(task);
    }

    public static Future<?> submit(Runnable runnable){
        return executor.submit(runnable);
    }
    public static void main(String args[]) throws ExecutionException, InterruptedException {

    }


}
