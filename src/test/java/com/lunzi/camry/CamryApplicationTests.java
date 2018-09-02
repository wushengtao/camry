package com.lunzi.camry;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.ao.SeqAO;
import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.domain.User;
import com.lunzi.camry.mapper.UserDao;
import com.lunzi.camry.service.DicService;
import com.lunzi.camry.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CamryApplicationTests {
    @Autowired
    SeqAO seqAO;
    @Autowired
    DicService dicService;
    private int count = 2;
    private CountDownLatch latch = new CountDownLatch(count);

    @Transactional
    @Test
    public void contextLoads() throws InterruptedException {
        Long start=System.currentTimeMillis();
        TestSync testSync = new TestSync();
        Thread thread1 = new Thread(testSync);
        thread1.start();
        Thread thread2 = new Thread(testSync);
        thread2.start();
        latch.await();
        System.out.println(System.currentTimeMillis()-start);
    }
    class TestSync implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                seqAO.test();
            }
            latch.countDown();

        }
    }
    @Test
    public void testgetTime() {

        for (int i = 0; i < 200000; i++) {
            System.out.println(seqAO.genCurrentTimp());
        }

    }
    @Test
    public void insert(){
            Dic dic=new Dic();
            dic.setKeyCode("c");
            dic.setValue(123L);
            dicService.insert(dic);
           // throw new RuntimeException();

    }

}

