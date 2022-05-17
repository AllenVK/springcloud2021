package com.paul.springcloud.service.impl;

import com.paul.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

/**
 * @author paul
 * @Description
 * @create 2021-11-13 19:56
 */
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "**PaymentHystrixServiceImpl fallback global method_OK**";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "**PaymentHystrixServiceImpl fallback global method_TimeOut**";
    }
}
