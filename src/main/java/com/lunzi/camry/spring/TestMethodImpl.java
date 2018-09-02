package com.lunzi.camry.spring;

import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/8/21 下午10:33
 */
@Component
public class TestMethodImpl implements TestMethod{
    @Override
    public void print() {
        System.out.println("this is test");
    }
}
