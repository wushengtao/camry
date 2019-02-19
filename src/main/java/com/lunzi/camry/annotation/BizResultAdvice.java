package com.lunzi.camry.annotation;

import java.lang.annotation.*;

/**
 * Created by lunzi on 2019/1/30 10:26 AM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BizResultAdvice {

}
