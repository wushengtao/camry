package com.lunzi.camry.annotation;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.service.DicService;
import com.lunzi.camry.service.impl.DicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.RoleUnresolved;

/**
 * Created by lunzi on 2018/5/30 下午8:07
 */
public class TestSynchronize implements Runnable {
    DicService dicService;

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (this) {
                EntityWrapper<Dic> wrapper=new EntityWrapper<>();
                wrapper.where("value={0}",1L);
                Dic dic= (Dic) dicService.selectOne(wrapper);
                System.out.println(dic.getValue());
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        TestSynchronize testSynchronize = new TestSynchronize();
        Thread thread1 = new Thread(testSynchronize);
        Thread thread2 = new Thread(testSynchronize);
        thread1.start();
        thread2.start();
    }

}
