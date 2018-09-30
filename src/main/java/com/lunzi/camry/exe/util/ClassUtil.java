package com.lunzi.camry.exe.util;

import com.lunzi.camry.domain.Student;
import com.lunzi.camry.exe.core.lang.ClassScaner;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lunzi on 2018/9/13 下午9:24
 */
public class ClassUtil {
    /**
     *
     * @param obj 获取对象的类型
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T obj){
        return (obj==null)?null: (Class<T>)obj.getClass();
    }

    /**
     * 获取类名
     * @param obj
     * @param isSimple
     * @return
     */
    public static String getClassName(Object obj,Boolean isSimple){
        if(obj==null){
            return null;
        }
        Class clazz=obj.getClass();
        return getClassName(clazz,isSimple);
    }

    /**
     * 重载方法 获取类名
     * @param clazz
     * @param isSimple
     * @return
     */
    public static String getClassName(Class<?> clazz,Boolean isSimple){
        if(clazz==null){
            return null;
        }
        return isSimple?clazz.getName():clazz.getSimpleName();
    }
    public static Class<?> [] getClasses(Object ... objects){
        Class<?> [] classes=new Class<?> [objects.length];
        Object object;
        for(int i=0;i<objects.length;i++){
            object=objects[i];
            classes[i]=(object==null)?null:object.getClass();
        }
        return classes;
    }

    public static boolean equals(Class<?> clazz,String className,boolean ignoreCase){
        if(clazz==null||StringUtils.isEmpty(className)){
            return false;
        }
        if(ignoreCase){
            return className.equalsIgnoreCase(clazz.getName())||className.equalsIgnoreCase(clazz.getSimpleName());
        }else {
            return className.equals(clazz.getName())||className.equals(clazz.getSimpleName());
        }
    }

    /**
     * 获取ClassPath
     * @param packageName
     * @param isDecode
     * @return
     */
    public static Set<String> getClassPath(String packageName, boolean isDecode) {
        String packagePath=packageName.replace(StrUtil.DOT,StrUtil.SLASH);
        Enumeration<URL> resources = null;
        try{
            resources=getClassLoader().getResources(packagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Set<String> paths=new HashSet<>();
        return paths;
    }
    /**
     * 获取{@link ClassLoader}<br>
     * 获取顺序如下：<br>
     *
     * <pre>
     * 1、获取当前线程的ContextClassLoader
     * 2、获取{@link ClassUtil}类对应的ClassLoader
     * 3、获取系统ClassLoader（{@link ClassLoader#getSystemClassLoader()}）
     * </pre>
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        return ClassLoaderUtil.getClassLoader();
    }
    public Set<Class<?>> scanPackageByAnnotation(String packageName,Class<? extends Annotation> clazz) throws IOException {
        if(StringUtils.isEmpty(packageName)){
            return null;
        }
        return ClassScaner.scanPackageByAnnotation(packageName,clazz);
    }
    public static void main(String args[]){
        System.out.println(getClassName(Student.class,false));
    }



}
