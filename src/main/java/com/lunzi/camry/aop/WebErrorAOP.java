package com.lunzi.camry.aop;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * 统一拦截web的错误test
 * Created by lunzi on 2018/10/21 上午10:30
 */
@Component
@Aspect
public class WebErrorAOP {
    @Pointcut("@annotation(com.lunzi.camry.aop.RecordLog)")
    public void log(){
        // 设置连接池
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        connMgr.setValidateAfterInactivity(1000);//超时时间自己定义一下

        RequestConfig.Builder configBuilder = RequestConfig.custom();
//        // 设置连接超时
//        configBuilder.setConnectTimeout(MAX_TIMEOUT);
//        // 设置读取超时
//        configBuilder.setSocketTimeout(MAX_TIMEOUT);
//        // 设置从连接池获取连接实例的超时
//        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        RequestConfig requestConfig; requestConfig = configBuilder.build();



    }
}
