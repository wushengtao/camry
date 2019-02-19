package com.lunzi.camry;

import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

/**
 * 测试java8的时间
 * Created by lunzi on 2018/11/8 下午11:44
 */
public class TestDate {
    public static void main(String[] args) {
//        LocalDateTime localDateTime=LocalDateTime.now();
//        System.out.println(localDateTime);
//        Period period=Period.ofDays(3);
//        LocalDateTime time=localDateTime.plus(period);
//        System.out.println(time);
        List<Long> list= Lists.newArrayList();
        list.add(null);
        System.out.println(list.size());
    }
}
