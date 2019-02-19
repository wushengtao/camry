package com.lunzi.camry.aop;

import java.lang.annotation.*;

/**
 * Created by lunzi on 2018/10/8 下午4:24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordLog {
    String method() default "";
}
