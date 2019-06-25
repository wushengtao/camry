package com.lunzi.camry.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lunzi on 2019/6/24 2:20 PM
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class LoginController {
    @GetMapping("/shiroLogin")
    public String doLogin() {
        Subject subject = (Subject) SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("wst","123"));
        return "sucesss";
    }
}
