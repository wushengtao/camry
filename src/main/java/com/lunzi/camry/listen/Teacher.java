package com.lunzi.camry.listen;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察的对象
 * Created by lunzi on 2018/7/23 下午8:43
 */
public class Teacher {
    List<Child> childList=new ArrayList<>();

    public void add(Child child){
        childList.add(child);
    }
    public void say(){
        System.out.println("publish homework");
        publish();
    }
    public void publish(){
        for(Child child:childList){
            child.get();
        }
    }
    public static void main(String args[]){
        Teacher teacher=new Teacher();
        Child child1=new Child(teacher);
        Child child2=new Child(teacher);
        teacher.say();
    }
}
