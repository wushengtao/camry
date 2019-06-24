package com.lunzi.camry.easyThreadPool;

import java.util.concurrent.Future;

/**
 * Created by lunzi on 2019/5/23 9:27 AM
 */
public interface EasyExecutorService extends EasyExecutor{
    Future<?> submit(Runnable task);
}
