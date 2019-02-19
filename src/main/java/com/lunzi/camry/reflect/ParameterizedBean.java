package com.lunzi.camry.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by lunzi on 2019/2/1 9:46 AM
 */
public class ParameterizedBean {
    List<String> list;
    String str;
    Integer i;

    public static void main(String[] args) {
       Field [] fields=ParameterizedBean.class.getDeclaredFields();
       for(Field field:fields){
            //如果是泛型参数，获取泛型参数的类型
           if(field.getGenericType() instanceof ParameterizedType){
               ParameterizedType parameterizedType= (ParameterizedType) field.getGenericType();
               System.out.println(parameterizedType.getActualTypeArguments());
           }
       }
    }
}
