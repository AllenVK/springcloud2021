package com.paul.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author paul
 * @Description
 * @create 2021-11-09 22:19
 */
@SpringBootApplication
@EnableFeignClients // 开启 OpenFeign 服务调用中心
public class OrderFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }

}
