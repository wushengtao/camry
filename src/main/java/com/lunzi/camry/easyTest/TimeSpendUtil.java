package com.lunzi.camry.easyTest;

import java.util.function.Function;

/**
 * Created by lunzi on 2019/6/14 3:58 PM
 */
public class TimeSpendUtil {
    public static <T, R> Function<T, R> timeSpend(Function<T, R> function) {
        return t -> {
            Long startTime = System.currentTimeMillis();
            R result = function.apply(t);
            System.out.println("time spend=" + (System.currentTimeMillis() - startTime));
            return result;
        };
    }

    public static int slowFunc(int num) {
        try {
            Thread.sleep(1000);
            System.out.println("123");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static void main(String[] args) {
        Function<Integer,Integer> function=TimeSpendUtil.timeSpend(TimeSpendUtil::slowFunc);
        function.apply(123);
    }
}
