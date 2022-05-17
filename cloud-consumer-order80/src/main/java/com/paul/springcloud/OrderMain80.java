package com.paul.springcloud;

import com.paul.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author paul
 * @Description
 * @create 2021-11-07 9:51
 */
@SpringBootApplication
@EnableEurekaClient
// 在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效，@RibbonClient
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }

}
