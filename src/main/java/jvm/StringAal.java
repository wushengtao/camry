package jvm;

import org.springframework.util.Assert;

/**
 * 分析String 的内存分配
 * Created by lunzi on 2019/3/6 9:04 PM
 */
public class StringAal {
    public static String q1="1";
    public static String q2="2";
    public static String q3="12";
    public static String q4=q1+q2;
    public static void main(String[] args) {
        //这里为false
        //在堆中创建对象的时候回去看字符串常量池有没有abc 没有就创建 然后引用，栈再指向堆
        String s1=new String("abc");
        String s2="abc";
        System.out.println(s1==s2);

        //""+""这种非符号拼接编译期间会优化字符串的拼接
        String a="a";
        String b="b";
        String ab="ab";
        String d="a"+"b";
        System.out.println(ab==d);

        //符号引用的拼接不会优化 通过StringBuilder的append
        String e="e";
        String f="f";
        String ef="ef";
        String g=e+f;
        System.out.println(ef==g);




    }
}
