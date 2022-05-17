package com.paul.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.paul.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author paul
 * @Description
 * @create 2021-11-12 22:45
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
    @HystrixCommand // 使用全局的 fallbackMethod
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        // 演示运行时异常
        int num = 6/0;

        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * 当调用的服务超时或者服务宕机等情况，不让客户等待，并返回一个友好提示，fallback
     * 一旦调用服务方法失败并抛出了错误信息后，会自动调用 @HystrixCommand 标注好的 fallbackMethod 调用类中的指定方法
     * @param id
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "消费者80，对方支付系统繁忙，请稍后重试，或检查自己是否有运行异常，₩";
    }

    /**
     * 全局的 fallback 方法
     * @return
     */
    public String payment_Global_FallbackMethod() {
        return "GlobalFallBackMethod异常处理信息，请稍后再试。";
    }

}
