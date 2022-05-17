package com.paul.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-05 13:17
 * @Author: paul
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  // 添加启动 Feign 功能
public class OrderNacosMain84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }

}
