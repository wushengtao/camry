package com.lunzi.camry.mq.test;

import com.lunzi.camry.domain.Student;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lunzi on 2018/9/18 下午8:02
 */

public class TestMq<Student> extends TestMqSuper<Student>{
    public void getType(){
        Type type=this.getClass().getGenericSuperclass();
        System.out.println(type);
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType=(ParameterizedType)type;
            Type [] types=parameterizedType.getActualTypeArguments();
            System.out.println(types[0]);
        }
    }
    public static void main(String agrs[]){

    }
}
class TestMqSuper<T>{

}