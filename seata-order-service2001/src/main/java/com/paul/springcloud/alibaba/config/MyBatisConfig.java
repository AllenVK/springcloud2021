package com.paul.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:12
 * @Author: paul
 */
@Configuration
@MapperScan({"com.paul.springcloud.alibaba.dao"})
public class MyBatisConfig {



}
