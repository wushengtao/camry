package com.lunzi.camry.core.convert;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by lunzi on 2018/6/19 下午3:18
 */
public enum BasicType {
    BYTE,SHORT,INT,INTEGER,LONG,DOUBLE,FLOAT,BOOLEAN,CHAR,CHARACTER,STRING;
    public static final Map<Class<?>,Class<?>> wrapperPrimitiveMap=new ConcurrentHashMap<>(8);
    public static final Map<Class<?>,Class<?>> primitiveWrapperMap=new ConcurrentHashMap<>(8);
    //静态初始化
    static{
        wrapperPrimitiveMap.put(Boolean.class,boolean.class);
        wrapperPrimitiveMap.put(Byte.class,byte.class);
        wrapperPrimitiveMap.put(Character.class,char.class);
        wrapperPrimitiveMap.put(Double.class,double.class);
        wrapperPrimitiveMap.put(Float.class,float.class);
        wrapperPrimitiveMap.put(Integer.class,int.class);
        wrapperPrimitiveMap.put(Long.class,long.class);
        wrapperPrimitiveMap.put(Short.class,short.class);
        for(Map.Entry<Class<?>,Class<?>> entry:wrapperPrimitiveMap.entrySet()){
            primitiveWrapperMap.put(entry.getValue(),entry.getKey());
        }
    }

    /**
     * 原始类转换成包装类，非原始类返回原类
     * @param clazz
     * @return
     */
    public static Class<?> wrap(Class<?> clazz){
        if(null==clazz||false==clazz.isPrimitive()){
            return clazz;
        }
        Class<?> result=primitiveWrapperMap.get(clazz);
        return (result==null)?clazz:result;
    }

    /**
     * 包装类转换成原始类，非包装类返回原类
     * @param clazz
     * @return
     */
    public static Class<?> unWrap(Class<?> clazz){
        if(null==clazz||clazz.isPrimitive()){
            return clazz;
        }
        Class<?> result=wrapperPrimitiveMap.get(clazz);
        return (result==null)?clazz:result;
    }



}
