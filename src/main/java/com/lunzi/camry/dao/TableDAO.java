package com.lunzi.camry.dao;

import org.springframework.cglib.proxy.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lunzi on 2018/5/29 下午2:49
 */
public class TableDAO {
    public void add() {
        System.out.println("this is add method");
    }

    public void query() {
        System.out.println("this is query method");
    }

    public static void main(String[] args) throws ParseException {
        AuthProxy authProxy = new AuthProxy("lunzi");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TableDAO.class);
        enhancer.setCallbacks(new Callback[]{authProxy,NoOp.INSTANCE});
        enhancer.setCallbackFilter(new AuthProxyFilter());
        TableDAO tableDAO = (TableDAO) enhancer.create();
        tableDAO.add();
    }

}

class AuthProxy implements MethodInterceptor {
    private String name;

    AuthProxy(String name) {
        this.name = name;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (!name.equals("lunzi")) {
            System.out.println("没有权限");
            return null;
        }
        return methodProxy.invokeSuper(o,objects);
    }
}
class AuthProxyFilter implements CallbackFilter{

    @Override
    public int accept(Method method) {
        if(!"query".equalsIgnoreCase(method.getName()))
            return 0;
        return 1;
    }
}

