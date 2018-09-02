package com.lunzi.camry.lang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式
 * Created by lunzi on 2018/6/18 上午10:30
 */
public class Singleton {
    private static Map<Class<?>, Object> map = new ConcurrentHashMap<>();

    private Singleton() {
    }
    public static <T> T get(Class<T> clz,Object...params) throws IllegalAccessException, InstantiationException {
        Object object=map.get(clz);
        if(object==null){
            synchronized (Singleton.class){
                if(object==null){


                }
            }
        }
        return null;
    }


}
