package com.lunzi.camry.web;

import com.lunzi.camry.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lunzi on 2019/1/30 10:12 AM
 */
@RequestMapping(value = "/test")
@Controller
public class TestBizController {
    @Autowired
    TestService testService;
    @ResponseBody
    @RequestMapping(value = "/biz")
    public String testController(){
        return testService.testBiz();
    }
}
