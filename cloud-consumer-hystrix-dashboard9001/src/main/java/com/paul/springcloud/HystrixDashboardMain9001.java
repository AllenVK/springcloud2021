package com.paul.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author paul
 * @Description
 * @create 2021-11-14 14:55
 */
@SpringBootApplication
@EnableHystrixDashboard // 开启 Hystrix 图形化
public class HystrixDashboardMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }

}
