package com.paul.springcloud.controller;

import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import com.paul.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author paul
 * @Description
 * @create 2021-11-09 22:34
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {

        // openFeign-ribbon，客户端默认等待1秒
        return paymentFeignService.paymentFeignTimeout();

    }

}
