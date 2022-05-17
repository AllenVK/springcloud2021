package com.paul.springcloud.controller;

import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import com.paul.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入操作影响行数：{}" + result);

        if (result > 0) {
            return new CommonResult(200, "success：" + serverPort, result);
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

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

}
