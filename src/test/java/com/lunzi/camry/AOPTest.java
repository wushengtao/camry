package com.lunzi.camry;

import com.lunzi.camry.aop.AOPDao;
import com.lunzi.camry.redis.RedisCacheTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lunzi on 2018/10/8 下午4:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AOPTest {
    @Autowired
    private AOPDao aopDao;
    @Autowired
    RedisCacheTemplate redisCacheTemplate;
    @Test
    public void test(){
        aopDao.print();
    }
}
