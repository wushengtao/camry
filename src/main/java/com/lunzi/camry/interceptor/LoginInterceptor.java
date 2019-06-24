package com.lunzi.camry.interceptor;

import com.lunzi.camry.domain.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.BitSet;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by lunzi on 2019/4/21 9:22 PM
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    //登录拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查本地的session
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

        //根据sessonId获取session
        User user = (User) httpServletRequest.getSession().getAttribute(sessionId);
        if(user!=null){
            return true;
        }

        response.sendRedirect("/test/login");
        return false;
    }

}
