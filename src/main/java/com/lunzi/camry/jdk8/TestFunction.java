package com.lunzi.camry.jdk8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by lunzi on 2018/12/10 1:54 PM
 */
public class TestFunction {

    private static void testPredicate(){
        List<Long> list= Lists.newArrayList();
        list.add(1L);
        list.add(2L);
        list.stream().filter((i)->i>0);
        HashMap hashMap= Maps.newHashMap();
        hashMap.put(null,"");
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> l=Lists.newArrayList();
        for(T id:list){
            if(p.test(id)){
                l.add(id);
            }
        }
        return l;
    }
    public static <T> void foreach(List<T> list, Consumer<T> consumer){
        for(T t:list){
            consumer.accept(t);
        }
    }

    public static Integer  function(Integer input,Function<Integer,Integer> function){
        Integer result=function.apply(input);
        return result;
    }
    public static void main(String arg[]){


    }
}
