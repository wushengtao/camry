package com.lunzi.camry.exe.util;


/**
 * Created by lunzi on 2018/9/19 下午5:08
 */
public class ClassLoaderUtil {
    /**
     * 获取当前线程的ClassLoader
     * @return
     */
    public static ClassLoader getContextClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static ClassLoader getClassLoader(){
        ClassLoader classLoader=getContextClassLoader();
        if(classLoader==null){
            classLoader=ClassLoaderUtil.class.getClassLoader();
            if(classLoader==null){
                classLoader=ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

}
