package com.paul.springcloud.alibaba.service.impl;

import com.paul.springcloud.alibaba.dao.OrderDao;
import com.paul.springcloud.alibaba.domain.Order;
import com.paul.springcloud.alibaba.service.AccountService;
import com.paul.springcloud.alibaba.service.OrderService;
import com.paul.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:10
 * @Author: paul
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    public void create(Order order) {

        /**
         * 当前应用创建订单
         */
        log.info("---->开始下单，创建订单<----");
        orderDao.create(order);

        /**
         * 远程调用库存服务扣减库存
         */
        log.info("---->order-service中扣减库存开始<----");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("---->order-service中扣减库存结束<----");

        /**
         * 远程调用账户服务扣减余额
         */
        log.info("---->order-service中扣减余额开始<----");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("---->order-service中扣减余额结束<----");

        /**
         * 修改订单状态为已完成
         */
        log.info("---->order-service中修改订单状态开始<----");
        orderDao.update(order.getUserId(), 0);
        log.info("---->order-service中修改订单状态结束<----");

        log.info("---->下单完成<----");

    }

}
