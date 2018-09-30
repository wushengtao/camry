package com.lunzi.camry;

import com.lunzi.camry.spring.SpringContextUtil;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lunzi.camry.*")
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class CamryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamryApplication.class, args);
    }
}