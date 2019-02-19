package com.lunzi.camry.controller;

import com.google.gson.Gson;
import com.lunzi.camry.ao.ExeAO;
import com.lunzi.camry.bean.BizResult;
import com.lunzi.camry.controller.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by lunzi on 2018/9/14 下午6:05
 */
@Controller
public class TestController {
    @Autowired
    private ExeAO exeAO;
    @RequestMapping(value = "testMap",method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestParam Map<String,String> map){
        System.out.println(new Gson().toJson(map));
        return "";
    }
    @GetMapping(value = "/testNginx")
    @ResponseBody
    public String testNginx(String name){
        return "testNginx";
    }
    @RequestMapping(value = "/hello")
    public String testHelo(){
        return "hello";
    }
    @RequestMapping(value = "/pic")
    public String testPic(){
        return "pic";
    }
    /**
     * 上传图片
     */
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile file){
        return "success";

    }
    /**
     * 测试并发
     */
    @ResponseBody
    @RequestMapping(value = "/testMysql")
    public String testMysql(){
        exeAO.testUpdate();
        return "sucess";
    }

    @ResponseBody
    @RequestMapping(value = "/testAspect")
    public String testAspect(@Valid @RequestBody UserForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("has errors");
        }
        return "sucess";
    }

    @ResponseBody
    @RequestMapping(value = "testUrl")
    public String testUrl(@ModelAttribute UserForm form){
        System.out.println(form.getUserName());
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "testException")
    public BizResult testException(@Valid UserForm userForm,BindingResult bindingResult){
        BizResult<Boolean> bizResult=BizResult.create(9999,"NO_DATA");
        throw new RuntimeException("test");
       // return bizResult;
    }

}
