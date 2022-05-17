package com.paul.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author paul
 * @Description
 * @create 2021-11-21 20:09
 */
@SpringBootApplication
@EnableDiscoveryClient  // 添加服务发现注解
public class OrderNacosMain83 {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain83.class, args);
    }

}
