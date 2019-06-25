package com.lunzi.camry.shiro;

import com.lunzi.camry.domain.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by lunzi on 2019/6/24 2:04 PM
 */
public class UserRealm extends AuthorizingRealm {


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //登录逻辑判断
        String password = token.getPassword().toString();
        String userName = (String) token.getPrincipal();
        User user=new User();
        user.setUserName(userName);
        user.setUserPwd(password);
        Button button=new Button();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
