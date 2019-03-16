package com.lunzi.camry.ao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.gson.Gson;
import com.lunzi.camry.ao.ExeAO;
import com.lunzi.camry.domain.Exe;
import com.lunzi.camry.mapper.ExeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lunzi on 2018/10/14 上午11:17
 */
@Component
public class ExeAOImpl  implements ExeAO {
    @Autowired
    private ExeDao exeDao;
    private Gson gson=new Gson();
    private static int i=0;
    private static ReentrantLock reentrantLock=new ReentrantLock();
    @Override
    @Transactional
    public void testUpdate() {
        reentrantLock.lock();
        Exe exe= exeDao.selectById(1L);
        exe.setValue(exe.getValue()+1);
        exe.setVersion(exe.getVersion());
        Integer num=exeDao.testUpdate(exe.getValue(),exe.getVersion()+1,exe.getId(),exe.getVersion());
        reentrantLock.unlock();
        if(num==0) {
            System.out.println("更新失败");
        }else {
            i++;
            System.out.println("成功更新了"+i+"条数据");
        }
        System.out.println(num);
    }

    @Override
    public void testAu() {
        System.out.println("this is a test");
    }

}
