package com.lunzi.camry;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.lunzi.camry.domain.Student;
import com.lunzi.camry.generics.TestGenerics;
import org.json.JSONObject;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/8/22 上午9:06
 */
public class TestGnetic<T>{
    public static void main(String args[]){
//        List<Long> list= Lists.newArrayList(null,null);
//        System.out.println(list.toString());
//        list=list.stream().filter(x->x!=null).collect(Collectors.toList());
//        System.out.println(list.size());
        List<Long> lists=Lists.newArrayList();
        lists.stream().forEach(x-> System.out.println());
        Student student=new Student();
        String s=JSON.toJSONString(student);
        com.alibaba.fastjson.JSONObject jsonObject= com.alibaba.fastjson.JSONObject.parseObject(s);
        String name=jsonObject.getString("name");
        List<Long> test=null;
        if(CollectionUtils.isEmpty(test)){
            System.out.println("123");
        }


    }
}
