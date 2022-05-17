package com.paul.springcloud.alibaba.service;

import com.paul.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:10
 * @Author: paul
 */
@FeignClient(value = "seata-storage-service") // 使用FeignClient注解，实现远程服务的调用
public interface StorageService {

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
