package com.lunzi.camry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by lunzi on 2018/9/26 下午5:32
 */
public class TestAnswer {
    public void test() {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        int sum=list.stream().parallel().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    public static void main(String[] args) {
        TestAnswer answer = new TestAnswer();
        answer.test();
    }
}
