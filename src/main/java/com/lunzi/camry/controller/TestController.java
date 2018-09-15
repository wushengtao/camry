package com.lunzi.camry.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by lunzi on 2018/9/14 下午6:05
 */
@Controller
public class TestController {
    @RequestMapping(value = "testMap",method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestParam Map<String,String> map){
        System.out.println(new Gson().toJson(map));
        return "";
    }


}
