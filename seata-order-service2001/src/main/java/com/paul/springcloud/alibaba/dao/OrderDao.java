package com.paul.springcloud.alibaba.dao;

import com.paul.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sun.awt.SunHints;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:09
 * @Author: paul
 */
@Mapper
public interface OrderDao {

    /**
     * 创建订单
     * @param order 订单实体类
     */
    void create(Order order);

    /**
     * 修改订单状态： 0 -> 1
     * @param userId 用户 id
     * @param status 订单状态
     */
    void update(@Param(value = "userId") Long userId, @Param(value = "status") Integer status);

}
