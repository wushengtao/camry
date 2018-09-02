package com.lunzi.camry.annotation;

import java.lang.annotation.*;

/**
 * 注解
 * Created by lunzi on 2018/5/29 下午8:04
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserCase {
    public int id() default -1;
    public String desc() default "no desc";
}
