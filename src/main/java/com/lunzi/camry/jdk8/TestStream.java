package com.lunzi.camry.jdk8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/11/16 10:14 AM
 */
public class TestStream {
    private int size;

    public static void main(String args[]){
        testStream();
    }
    public static void testStream(){
        List<Integer> list= Lists.newArrayList();
        list.add(5);
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
        list=list.stream().filter(x->x>1)
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .collect(Collectors.toList());
        System.out.println(list);
        Long count= list.stream().filter(x->x>1)
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .count();
        System.out.println(count);
        String [] words={"hello","word"};
        List l=Arrays.stream(words).map(x->x.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(l);
    }
}
