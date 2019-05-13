package com.lunzi.camry.service.impl;

import com.lunzi.camry.annotation.BizResultAdvice;
import com.lunzi.camry.mapper.ZhUserDao;
import com.lunzi.camry.service.TestService;

import com.lunzi.simple.starter.anno.DisLock;
import com.lunzi.simple.starter.anno.ZkDisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lunzi on 2018/6/10 下午6:52
 */
@Component
public class TestServiceImpl implements TestService {
    @Autowired
    ZhUserDao zhUserDao;
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
    //@ZkDisLock(value = "test")
    @DisLock(key= "redisLock",keepMills = 3000)
    public String lock() {
        System.out.println("加锁,线程睡眠一会");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    @Transactional
    public void lockCon() {
        zhUserDao.selectForUpdateById();
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
