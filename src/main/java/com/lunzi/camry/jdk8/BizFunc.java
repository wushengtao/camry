package com.lunzi.camry.jdk8;

/**
 * Created by lunzi on 2019/2/28 11:26 PM
 */

public interface BizFunc<T,R> {
    R get(T t);
}
