package com.lunzi.camry.jdk8;


import java.util.concurrent.*;

/**
 * Created by lunzi on 2019/5/6 9:37 AM
 */
public class ComletionServiceTest {
    public void test(){
        ExecutorService executorService=Executors.newFixedThreadPool(1);
        BlockingQueue<Future<Integer>> queue=new LinkedBlockingDeque<Future<Integer>>(10);
        CompletionService completionService=new ExecutorCompletionService<Integer>(executorService);
    }
}
