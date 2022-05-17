package com.paul.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author paul
 * @Description
 * @create 2021-11-07 10:02
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced  // 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    //@LoadBalanced  自定义负载均衡算法需要注释掉该注解
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

}
