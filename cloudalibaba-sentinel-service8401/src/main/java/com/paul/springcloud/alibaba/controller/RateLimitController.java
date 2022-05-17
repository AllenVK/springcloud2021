package com.paul.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.paul.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-05 10:29
 * @Author: paul
 */
@RestController
@Slf4j
public class RateLimitController {

    /**
     * 注意：如果与 sentinel 设置的通过 url 限流 @SentinelResource(value = "byUrl")，一起使用
     *      这里通过指定 blockHandler 方法就会失效，出现页面报错
     *
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试OK", new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444,exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url限流测试OK", new Payment(2020L,"serial002"));
    }

    /**
     * 使用用户自定义的通用限流 fallback 方法
     *
     * @SentinelResource - value：在 Sentinel 管理页面配置的监控名称
     * @SentinelResource - blockHandlerClass：指定客户自定义的限流处理逻辑
     * @SentinelResource - blockHandler：指定客户自定义类中 blockHandler 方法
     *
     * @return
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
                      blockHandlerClass = CustomerBlockHandler.class,
                      blockHandler = "handleException1")
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客户自定义限流处理逻辑");
    }

}
