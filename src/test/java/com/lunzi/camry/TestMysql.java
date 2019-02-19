package com.lunzi.camry;

import com.lunzi.camry.ao.ExeAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lunzi on 2018/10/14 下午2:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMysql {
    @Autowired
    private ExeAO exeAO;
    @Test
    public void test(){
        exeAO.testUpdate();
    }
}
