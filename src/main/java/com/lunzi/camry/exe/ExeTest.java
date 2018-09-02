package com.lunzi.camry.exe;

import sun.awt.windows.ThemeReader;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/8/22 下午10:34
 */
public class ExeTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);//线程池
    private static Object object=new Object();
    private static Integer sync=1;
    private static ReentrantLock reentrantLock=new ReentrantLock();
    private static Condition condition=reentrantLock.newCondition();
    private static AtomicInteger atomic=new AtomicInteger(1);
    private static CyclicBarrier barrier=new CyclicBarrier(2,new CycAction());
    private static List<Integer> list=Collections.synchronizedList(new ArrayList<>(2));
    private static LinkedBlockingQueue<Integer> queue=new LinkedBlockingQueue<>();
    public static void main(String args[]) {
        Runnable one = new Runnable() {
            @Override
            public void run() {
                Integer name=1;
                for (int i = 0; i < 10; i++) {
                    System.out.print(1);
                    queue.offer(0);
                    while(!name.equals(queue.peek())){
                    }
                    queue.poll();


                }
            }
        };
        Runnable two = new Runnable() {
            @Override
            public void run() {
                Integer name=0;
                for (int i = 10; i < 20; i++) {
                    while(!name.equals(queue.peek())){
                    }
                    queue.poll();
                    System.out.print(0);
                    queue.offer(1);

                }
            }
        };
        executorService.submit(one);
        executorService.submit(two);
        executorService.shutdown();
    }

    static class CycAction implements Runnable{

        @Override
        public void run() {
            //做一下排序 保证1在0前面
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o1>o2){
                        return -1;
                    }
                    else if(o1<o2){
                        return 1;
                    }
                    else if(o1.equals(o2)){
                        return 0;
                    }
                    return -1;
                }
            });
            list.forEach(x-> System.out.print(x));
            list.clear();
        }
    }
}


