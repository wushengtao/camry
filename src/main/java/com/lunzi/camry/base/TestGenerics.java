package com.lunzi.camry.base;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lunzi on 2018/6/16 下午8:04
 */
public class TestGenerics {
    public static <K,V> Map<K,V> getMap(){
        String key="age";
        String value="1";
        Map<K,V> map=new HashMap<>();
        map.put((K)key,(V)value);
        return map;
    }
    public static <K, V> Map<K, V> getMap(String source, String firstSplit, String secondSplit) {

        Map<K, V> result = new HashMap<K, V>();
        if (source.equals("")) {
            return result;
        }
        String[] strings = source.split(firstSplit);
        for (int i = 0; i < strings.length; i++) {
            String[] tmp = strings[i].split(secondSplit);
            if (tmp.length == 2) {
                result.put((K) tmp[0], (V) tmp[1]);
                // System.out.println("(K) tmp[0]:"+((K) tmp[0]).getClass());
                // System.out.println("(V) tmp[1]:"+((V) tmp[1]).getClass());
            }
        }

        return result;
    }
    public static void main(String[] args) {
//        Map<String,Integer> m=getMap();
////        for(Map.Entry<String,Integer> entry:m.entrySet()){
////            Integer value=entry.getValue();
////        }
        String name = "123";
        System.out.println((Object) "123");
    }
}
