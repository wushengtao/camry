package com.lunzi.camry.tool.core.util;


import com.lunzi.camry.tool.core.lang.SimpleCache;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * Created by lunzi on 2018/7/19 上午8:27
 */
public class ReflectUtil {
    /**
     * 构造对象缓存
     */
    private static final SimpleCache<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new SimpleCache<>();
    /**
     * 字段缓存
     */
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();
    /**
     * 方法缓存
     */
    private static final SimpleCache<Class<?>, Method[]> METHODS_CACHE = new SimpleCache<>();

    /**
     * 查找类中的指定参数的构造方法
     * @param clazz
     * @param paramterType
     * @param <T>
     * @return
     */
    public static <T> Constructor<T> getConstructor(Class<T> clazz,Class<?>...paramterType){
        if(null==clazz){
            return null;
        }
        final Constructor<?>[] constructors=clazz.getConstructors();
        Class<?>[] pts;
        for(Constructor<?> constructor:constructors){
            pts=constructor.getParameterTypes();
            if(ClassUtil.isAllAssignableFrom(pts,paramterType)){
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }

    /**
     * 调用方法 封装了一层
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static <T> T invoke(Object obj, Method method, Object[] args) throws RuntimeException{
        if(false==method.isAccessible()){
            method.setAccessible(true);
        }
        try{
            return (T)method.invoke(ClassUtil.isStatic(method)?null:obj,args);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
