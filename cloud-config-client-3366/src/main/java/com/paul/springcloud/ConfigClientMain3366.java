package com.paul.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author paul
 * @Description
 * @create 2021-11-18 22:28
 */
@SpringBootApplication
@EnableEurekaClient  // 开启 Eureka 客户端
public class ConfigClientMain3366 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class, args);
    }

}
