package com.lunzi.camry.lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 *
 * Created by lunzi on 2019/3/8 7:52 PM
 */
public class SyncCal {
    public volatile  int i=0;
    public  synchronized int  add(){
        return i++;
    }
    public synchronized int read(){
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(10000);
        //定义一个线程池
        ThreadPoolExecutor executor
                =new ThreadPoolExecutor(20,
                20, 100,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(100000));
        SyncCal syncCal=new SyncCal();
        //模拟1000个线程
        IntStream.range(0,10000).forEach(num->{
            executor.execute( new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(num+"设置"+syncCal.add());
                    System.out.println(num+"读取"+syncCal.read());
                    latch.countDown();
                }
            }));

        });
        latch.await();
//        System.out.println("读取"+syncCal.i);
//        System.out.println("读取"+syncCal.read());
    }
}
