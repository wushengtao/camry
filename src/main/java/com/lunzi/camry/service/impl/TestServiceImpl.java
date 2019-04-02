package com.lunzi.camry.service.impl;

import com.lunzi.camry.annotation.BizResultAdvice;
import com.lunzi.camry.service.TestService;

import com.mhc.framework.support.lock.annotations.DisLock;
import org.springframework.stereotype.Component;

/**
 * Created by lunzi on 2018/6/10 下午6:52
 */
@Component
public class TestServiceImpl implements TestService {
    @Override
    public void add() {
        System.out.println("this is add");
    }

    @Override
    @BizResultAdvice
    public String testBiz() {
        return "test Biz";
    }

    @Override
    @DisLock(key="test")
    public String lock() {
        System.out.println("加锁");
        return "success";
    }
}
