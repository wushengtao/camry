package com.lunzi.camry.annotation;

import org.apache.ibatis.io.ResolverUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by lunzi on 2018/10/20 下午9:26
 */
public class TestAnnotation {
    @UserCase(id=1,desc = "test")
    public void test(){
        System.out.println("this is method test");
    }
    public static void main(String args[]){
        Class clazz=TestAnnotation.class;
        Method[] methods=clazz.getDeclaredMethods();
        Arrays.stream(methods).forEach(x->{
            Annotation[] annotations=x.getAnnotations();
            if(annotations.length>0){
                for(Annotation annotation:annotations){
                    System.out.println(annotation.annotationType().getName());
                }
            }
        });
    }
}
