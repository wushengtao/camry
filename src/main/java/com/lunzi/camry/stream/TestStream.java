package com.lunzi.camry.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunzi on 2018/7/12 上午8:36
 */
public class TestStream {
    public static List<Apple> filterApples(List<Apple> list,ApplePredicate p){
        List<Apple> result=new ArrayList<>();
        for(Apple apple:result){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
    public static void main(String args[]){
        Integer nun=12;
        Integer num2=12;
        System.out.println(nun.equals(num2));
    }

}
