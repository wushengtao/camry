package com.lunzi.camry.lock;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * 等待唤醒机制
 * Created by lunzi on 2019/6/3 11:29 PM
 */
public class GuardedObject<T> {
    @Getter
    @Setter
    volatile T obj;//受保护的对象

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public T get(Predicate<T> p) {
        //锁起来
        lock.lock();
        try {
            while (!p.test(obj)) {
                condition.await(1, TimeUnit.SECONDS);
                System.out.println("挂起...");
                System.out.println(obj);
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return obj;
    }

    public void onChange(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        GuardedObject<String> guardedObject = new GuardedObject<>();
//        String result = guardedObject.get(t -> Objects.equals(t, "success"));
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//                System.out.println("设置值");
//                guardedObject.setObj("success");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.start();
//        System.out.println(result);
        List<String> mapList = Lists.newArrayList();
        if (mapList != null && mapList.size() == 1) {
            System.out.println("123");
        } else if (mapList.size() > 1) {
            System.out.println("1234");
        }


    }

}
