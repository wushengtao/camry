package com.lunzi.camry.ao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.ao.MethodLockAO;
import com.lunzi.camry.domain.MethodLock;
import com.lunzi.camry.mapper.MethodLockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by lunzi on 2018/9/12 上午10:56
 */
@Component
public class MethodLockAOImpl implements MethodLockAO {
    @Autowired
    MethodLockDao methodLockDao;
    @Override
    public boolean getLock(String lockName) {
        MethodLock methodLock=new MethodLock();
        methodLock.setLockName(lockName);
        methodLock.setLockCreate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        methodLock.setLockModified(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        try {
            methodLockDao.insert(methodLock);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean getLockByName(String lockName) {
        try {
            methodLockDao.selectLockForUpdate(lockName);
            Thread.sleep(100000);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
