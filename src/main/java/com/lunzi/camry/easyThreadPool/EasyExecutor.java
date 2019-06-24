package com.lunzi.camry.easyThreadPool;

/**
 * 线程池执行器
 * Created by lunzi on 2019/5/23 9:16 AM
 */
public interface EasyExecutor {
    void execute(Runnable runnable);
}
