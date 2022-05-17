package com.paul.springcloud.alibaba.service;

import com.paul.springcloud.alibaba.domain.Order;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:09
 * @Author: paul
 */
public interface OrderService {

    /**
     * 创建表单
     * @param order
     */
    void create(Order order);

}
