package com.paul.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Desc: 使用 Seata 对自定义的数据源进行代理
 * @Date: 2021-12-06 22:13
 * @Author: paul
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 取消数据源的自动创建
@EnableFeignClients
@EnableDiscoveryClient
public class SeataOrderMainApp2001 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp2001.class, args);
    }

}
