package com.lunzi.camry.aop;

import java.lang.annotation.*;

/**
 * 计算运行的时间
 * Created by lunzi on 2019/5/18 9:36 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CountRunTime {
    String value() default "";
}
