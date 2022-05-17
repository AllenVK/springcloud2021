package com.paul.springcloud.service.impl;

import com.paul.springcloud.dao.PaymentDao;
import com.paul.springcloud.entities.Payment;
import com.paul.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author paul
 * @Description
 * @create 2021-11-06 22:07
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
