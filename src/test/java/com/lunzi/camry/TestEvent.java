package com.lunzi.camry;

import com.lunzi.camry.design.listen.event.CommonListener;
import com.lunzi.camry.design.listen.event.CommonPublish;
import com.sun.xml.internal.stream.events.CommentEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lunzi on 2018/8/21 上午12:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEvent{
    @Autowired
    private CommonPublish commonPublish;
    @Test
    public void testEvent(){
        commonPublish.register("lunzi");
    }
}
