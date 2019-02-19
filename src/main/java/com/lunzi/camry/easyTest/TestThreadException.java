package com.lunzi.camry.easyTest;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/11/15 3:47 PM
 */
public class TestThreadException {
    public static void main(String args[]){
        List<String> list=Lists.newArrayList();
        list.add("123");
        list.add("123");
        List<String> testList=list.stream().map(x->{
            return "123";
        }).collect(Collectors.toList());
        System.out.println(testList);
    }

}
