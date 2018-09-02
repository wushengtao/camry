package com.lunzi.camry.innerClass;

import java.util.HashMap;
import java.util.Map;

/**
 * 成员内部类
 * Created by lunzi on 2018/6/18 下午1:42
 */
public class ClassRoom {
    private int num;
    private static int room;
    class Student{
        int s=0;
        public void prineNum(){
            System.out.println(num);
        }
    }
    public static void main(String args[]){
//        ClassRoom.Student student=new ClassRoom().new Student();
////        student.prineNum();
////        ClassRoom.Teacher teacher=new ClassRoom.Teacher();
////        teacher.print();
        Map<Integer,Integer> map=new HashMap();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        System.out.println(map.toString());
        Integer i=map.get(1);
        i=20;
        System.out.println(map.toString());


    }
    public void defineClass(){
        class Man{

        }
    }
    static class Teacher{
        public void print(){
            System.out.println(room);
        }

    }

    public static int getRoom() {
        return room;
    }

    public static void setRoom(int room) {
        ClassRoom.room = room;
    }

}
