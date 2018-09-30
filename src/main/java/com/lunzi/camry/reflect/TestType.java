package com.lunzi.camry.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by lunzi on 2018/9/19 下午10:27
 */
public class TestType<T> {
    private List<String> list=null;
    private Map<String,Long> map=null;
    public static void main(String args[]) throws NoSuchFieldException {
        Field field=TestType.class.getDeclaredField("map");
        Type type=field.getGenericType();
        ParameterizedType parameterizedType=(ParameterizedType)type;
        Type [] types=parameterizedType.getActualTypeArguments();
        Type t=parameterizedType.getRawType();
        System.out.println(types.length);
        System.out.println();
    }
}
