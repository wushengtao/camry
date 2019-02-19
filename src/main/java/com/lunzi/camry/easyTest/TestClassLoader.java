package com.lunzi.camry.easyTest;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * 测试类加载器相关的内容
 * Created by lunzi on 2018/11/24 11:04 AM
 */
public class TestClassLoader {
    public static void main(String[] args) {
        String classPath=System.getProperty("java.class.path");
        String [] classPaths=classPath.split(":");
        //Arrays.stream(classPaths).forEach(s-> System.out.println(s));
        //System.out.println(TestClassLoader.class.getClassLoader());
        List<String> stringList= Lists.newArrayList();
        stringList.add("123");
        stringList.add("123");
        Set<String> set= Sets.newHashSet();
        set.addAll(stringList);
        System.out.println(set);
        System.out.println(set.size());
        Integer i=null;
        //assert false:"控制值";
        System.out.println("12");
        Boolean isSuccess=null;
        System.out.println(isSuccess);
        Map<String,List<Long>> map=new HashMap<>();
        map.put("1",Lists.newArrayList(1L));
        map.put("2",Lists.newArrayList(2L));
        System.out.println(map.values());
        List<Long> list=null;
        for(Long a:list){
            System.out.println(a);
        }
    }
}
