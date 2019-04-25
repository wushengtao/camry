package com.lunzi.camry.web;

import com.lunzi.camry.domain.User;
import com.lunzi.camry.mapper.ZhUserDao;
import com.lunzi.camry.service.TestService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

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
    public String testController() {
        return testService.testBiz();
    }

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login() {
        return "登录中心";
    }

    @ResponseBody
    @RequestMapping(value = "/loginForm")
    public String loginForm() {
        //自动登录成功
        //设置session
        //获取从cookie中获取sessionId
        String sessionId = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取httpRequest
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) requestAttributes).getResponse();
        //获取cookie
        Cookie[] cookies = httpServletRequest.getCookies();
        if (!ArrayUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (Objects.equals("loginSessionId", cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
        }
        if (Objects.equals(sessionId, "")) {
            //生成sessionId
            sessionId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("loginSessionId", sessionId);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }

        //设置sessionId
        User user=new User();
        user.setUserId(1L);
        httpServletRequest.getSession().setAttribute(sessionId,user);

        return "登录成功";
    }

    /**
     * 测试数据库连接池的数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/testMysqlCon")
    public String testMysqlCOn(){
        testService.lockCon();
        return "success";
    }
}
