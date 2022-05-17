package com.paul.springcloud.service;

import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author paul
 * @Description
 * @create 2021-11-09 22:25
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")  // 去 Eureka 上面指定名称的微服务接口
public interface PaymentFeignService {

    /**
     * /payment/getById/{id}
     * 这里请求的映射地址要和对应服务上面对外暴露的请求路径一样
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/getById/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();

}
