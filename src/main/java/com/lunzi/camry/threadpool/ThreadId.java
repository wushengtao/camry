package com.lunzi.camry.threadpool;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
 *Created by lunzi on 2019/3/12 4:25 PM
 */
public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        List<Integer> list= Lists.newArrayList();
        list.add(3);
        list=list.stream().filter(num->!num.equals(3)).collect(Collectors.toList());
        List<Integer> l=Lists.newArrayList();
        l.addAll(list);
        System.out.println(list);
    }
}
