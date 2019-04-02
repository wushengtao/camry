package com.lunzi.camry.lock;

import java.lang.annotation.*;

/**
 * reentLock锁的注解
 * Created by lunzi on 2019/4/1 3:42 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReentLock {
     String value() default "";
}
