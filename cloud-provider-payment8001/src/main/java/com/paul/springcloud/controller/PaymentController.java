package com.paul.springcloud.controller;

import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import com.paul.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author paul
 * @Description
 * @create 2021-11-06 22:11
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入操作影响行数：{}" + result);

        if (result > 0) {
            return new CommonResult(200, "query success：" + serverPort, result);
        } else {
            return new CommonResult(500, "failed", null);
        }
    }

    @GetMapping("/payment/getById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}" + payment);

        if (payment != null) {
            return new CommonResult(200, "query success：" + serverPort, payment);
        } else {
            return new CommonResult(500, "query failed：" + id, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        // 获得服务列表
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("当前服务：{}", service);
        }

        // 一个微服务下的全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back.";
    }

}
