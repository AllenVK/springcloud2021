package com.paul.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import com.paul.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-05 13:20
 * @Author: paul
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    /**
     *
     * @SentinelResource(value = "fallback", fallback = "handlerFallback") : 没有配置对应的 fallback 方法
     * @SentinelResource(value = "fallback", fallback = "handlerFallback") : fallback 负责业务异常，负责 java 运行时异常
     * @SentinelResource(value = "fallback", blockHandler = "blockHandler"): blockHandler 只负责 Sentinel 控制台配置的规则
     *
     * @SentinelResource(value = "fallback",
     *                       fallback = "handlerFallback",
     *                       blockHandler = "blockHandler",
     *                       exceptionsToIgnore = {IllegalArgumentException.class})
     *
     * exceptionsToIgnore = {IllegalArgumentException.class, ...}：设置额可以忽略哪些异常不需要管控
     *
     * 若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
     *
     * @param id
     * @return
     */
    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",
                      fallback = "handlerFallback",
                      blockHandler = "blockHandler",
                      exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    /**
     * fallback 方法
     * @param id
     * @param e
     * @return
     */
    public CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
    }

    /**
     * blockHandler 方法
     *
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
    }

    //==================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        if(id == 4) {
            throw new RuntimeException("没有该id");
        }
        return paymentService.paymentSQL(id);
    }

}
