package com.paul.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.paul.springcloud.entities.CommonResult;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-05 10:55
 * @Author: paul
 */
public class CustomerBlockHandler {

    public static CommonResult handleException1(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler---1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler---2");
    }

}
