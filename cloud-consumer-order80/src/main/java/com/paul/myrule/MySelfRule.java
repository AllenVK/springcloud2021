package com.paul.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paul
 * @Description
 * @create 2021-11-08 22:20
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // Ribbon 默认的算法为随即算法
        return new RandomRule(); // 定义 Ribbon 选择服务的算法为随机算法
    }

}
