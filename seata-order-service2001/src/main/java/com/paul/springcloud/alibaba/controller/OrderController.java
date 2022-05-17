package com.paul.springcloud.alibaba.controller;

import com.paul.springcloud.alibaba.domain.CommonResult;
import com.paul.springcloud.alibaba.domain.Order;
import com.paul.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:12
 * @Author: paul
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功!");
    }

}
