package com.lunzi.camry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.lunzi.simple.starter.service.StarterService;

/**
 * Created by lunzi on 2019/3/9 10:55 AM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAuto {
    @Autowired
    StarterService  starterService;
    @Test
    public void test(){
        starterService.printConfig();
    }

}
