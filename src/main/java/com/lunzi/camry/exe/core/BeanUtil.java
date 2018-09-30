package com.lunzi.camry.exe.core;

import java.lang.reflect.Method;

/**
 * Created by lunzi on 2018/9/13 下午8:58
 */
public class BeanUtil {
    /**
     * 判断是否为标准bean hutool貌似有问题
     * @param clazz
     * @return
     */
    public static boolean isBean(Class<?> clazz){
        //判断是否为标准的类
        final Method [] methods=clazz.getMethods();
        for(Method method:methods){
            if(method.getParameterTypes().length==1&&method.getName().startsWith("set")){
                return true;
            }
        }
        return false;
    }


}
