package com.lunzi.camry.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by lunzi on 2018/6/5 下午10:33
 */
@Data
public class Student implements Serializable {
    private String name;
    private Integer age;

    //---------------构造方法-------------------
    //（默认的构造方法）
    public Student(String name){
        this.name=name;
    }

    //无参构造方法
    public Student(){
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    //受保护的构造方法
    protected Student(boolean n){
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Student(int age){
        System.out.println("私有的构造方法   年龄："+ age);
    }


}
