package com.lunzi.camry.easyTest;
import com.alibaba.fastjson.JSONObject;
import com.lunzi.camry.domain.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by lunzi on 2019/1/3 2:21 PM
 */
public class TestList {
    public static void main(String[] args) {
        TestList testList=new TestList();
        String str="12.222万";
        BigDecimal bigDecimal=new BigDecimal(str.replace("万",""));
        BigDecimal big=bigDecimal.multiply(new BigDecimal(1000000));
        System.out.println(big.longValue());
        String pattern = "^S[0-9]+$";
        boolean isMatch = Pattern.matches(pattern, "S2012");
        System.out.println(isMatch);
        Student student=new Student();
        student.setAge(1);
        student.setName("123");
        JSONObject jsonObject=JSONObject.parseObject(JSONObject.toJSON(student).toString());
        JSONObject object=jsonObject.getJSONObject("test");

        //testList.test();
        //testList.testSort();
    }
    public void test(){
        Student student=new Student("0");
        Long id=1L;
        change1(student,id);
        change2(student,id);
    }
    public void change1(Student student,Long id){
        id=2L;
        student.setName("1");
    }
    public void change2(Student student,Long id){
        System.out.println(student.getName());
        System.out.println(id);
    }
    public void testSort(){
        Student s1= new Student("3",1);
        Student s2 = new Student("2",1);
        Student s3=new Student("1",1);
        List<Student> list=new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.iterator();
        list.stream().max(Comparator.comparing(Student::getAge));
        System.out.println(list);
        System.out.println(list.stream().max(Comparator.comparing(Student::getAge)).get());
    }

}
