package com.lunzi.camry.exe.util;

import com.lunzi.camry.domain.Student;
import org.apache.commons.lang.StringUtils;
import org.jboss.netty.util.internal.StringUtil;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by lunzi on 2018/9/13 下午9:24
 */
public class ClassUtil {
    /**
     *
     * @param obj
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

    public Set<Class<?>> scanPackageByAnnotation(String packageName,Class<? extends Annotation> clazz){
        if(StringUtils.isEmpty(packageName)){
            return null;
        }
        return null;
    }
    public static void main(String args[]){
        System.out.println(getClassName(Student.class,false));
    }



}
