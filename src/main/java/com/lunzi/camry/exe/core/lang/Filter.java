package com.lunzi.camry.exe.core.lang;

/**
 * Created by lunzi on 2018/9/14 上午11:25
 */
public interface Filter<T> {
    boolean accept(T t);
}
