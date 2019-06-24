package com.lunzi.camry.easyThreadPool;

/**
 * Created by lunzi on 2019/5/23 9:58 AM
 */
public class EasyExecutors {
    public static EasyExecutorService newFixedThreadPool(int threadNum){
        return new EasyThreadPoolExecutor(threadNum);
    }


}
