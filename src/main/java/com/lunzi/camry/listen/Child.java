package com.lunzi.camry.listen;

/**
 * Created by lunzi on 2018/7/23 下午8:45
 */
public class Child {
    private Teacher teacher;
    public Child(Teacher teacher){
        teacher.add(this);
    }
    public void get(){
        System.out.println("i get");
    }
}
