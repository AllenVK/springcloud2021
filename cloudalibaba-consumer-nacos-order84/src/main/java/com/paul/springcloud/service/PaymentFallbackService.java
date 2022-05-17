package com.paul.springcloud.service;

import com.paul.springcloud.entities.CommonResult;
import com.paul.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-05 16:33
 * @Author: paul
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回,没有该流水信息",new Payment(id, "errorSerial......"));
    }

}
