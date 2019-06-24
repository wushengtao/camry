package com.lunzi.camry.easyThreadPool;

import com.lunzi.camry.mybatis.EasySqlSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * Created by lunzi on 2019/5/23 9:17 AM
 */
public abstract class EasyAbstractExecutorService implements EasyExecutorService {
    @Override
    public Future<?> submit(Runnable task) {
        if(task==null) throw new NullPointerException();
        RunnableFuture<Void> runnableFuture=newTaskFor(task,null);
        execute(runnableFuture);
        return runnableFuture;
    }

    private <T> RunnableFuture<T> newTaskFor(Runnable task, T value) {
        return new FutureTask<>(task,value);
    }
}
