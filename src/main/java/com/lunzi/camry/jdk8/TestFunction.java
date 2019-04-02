package com.lunzi.camry.jdk8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/12/10 1:54 PM
 */
public class TestFunction {

    private static void testPredicate() {
        List<Long> list = Lists.newArrayList();
        list.add(1L);
        list.add(2L);
        list.stream().filter((i) -> i > 0);
        HashMap hashMap = Maps.newHashMap();
        hashMap.put(null, "");
    }

    public void testConsumer(Consumer<Double> consumer, Double num) {
        consumer.accept(num);
    }

    public void consume() {
        testConsumer(this::add, 12d);
    }

    public void add(Double num) {
        System.out.println(num);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = Lists.newArrayList();
        list.forEach(t -> {
            if (p.test(t)) {
                result.add(t);
            }
        });
        return result;
    }

    public void testFilter(){
        List<Integer> list=Lists.newArrayList();
        filter(list, this::pre);
    }

    public void testFunction(Function<Integer,Integer> function,Integer input){
        function.apply(input);
    }

    public void testFu(){
        testFunction(this::testF,1);
    }

    public Integer testF(Integer num){
        return 0;
    }

    public boolean pre(Integer num){
        return true;
    }


    public static <T> void foreach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static Integer function(Integer input, Function<Integer, Integer> function) {
        Integer result = function.apply(input);
        return result;
    }

    public static void main(String arg[]) {
        List<String> list=Lists.newArrayList("物流费","仓储费");
        String str=String.join("、",list);
        System.out.println(str);
    }
}
