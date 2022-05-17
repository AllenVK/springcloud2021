package com.paul.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paul
 * @Description
 * @create 2021-11-16 22:31
 */
@RestController
@RefreshScope
public class ConfigClientController {

    /**
     * 注意：@Value("${config.info}") 里面的 config.info 是 3344 服务端在 github 中的 config-xxx.yml 的内容
     * 所以首先要保证 3344 端口服务的的 config-xxx.yml 配置文件内容能访问到
     */
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }

}
