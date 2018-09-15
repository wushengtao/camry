package com.lunzi.camry.reflect;

import com.google.common.collect.Lists;
import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.domain.Student;
import jdk.internal.dynalink.linker.MethodHandleTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/6/5 下午10:20
 */
public class Reflect {
    /**
     * 获取Class对象
     */
    public static void getObjectClass() throws Exception {
        Class clz1 = Dic.class;
        Class clz2 = new Dic().getClass();
        Class clz3 = Class.forName("com.lunzi.camry.domain.Dic");
        System.out.println(clz1);
        System.out.println(clz2);
        System.out.println(clz3);
    }

    public static void getobjectConstruction() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class clz = Student.class;
        Constructor constructor=clz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        //System.out.println(constructor.getName());
        System.out.println(constructor.newInstance("wst").toString());
    }
    public static void methodInvoke(){
        Student student=new Student();
        Class clz=Student.class;
        Method[] methods=clz.getDeclaredMethods();
        try {
            Method method=clz.getMethod("setName", String.class);
            method.invoke(student,"wst");
            Method method1=clz.getMethod("getName");
            System.out.println(method1.invoke(student));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getMethod(){

    }
    public static void main(String[] args) throws Exception {
       Reflect.getobjectConstruction();
        //Reflect.methodInvoke();
        Student student=new Student();
        student.setName("123");
        Student student1=new Student();
        student1.setName("123");
        List<Student> list= Lists.newArrayList(student,student1);
        System.out.println(list.stream().map(Student::getName).collect(Collectors.toList()));
    }
}
