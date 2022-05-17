package com.paul.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-01 21:48
 * @Author: paul
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与返现注解
public class MainApp8401 {

    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class, args);
    }

}
