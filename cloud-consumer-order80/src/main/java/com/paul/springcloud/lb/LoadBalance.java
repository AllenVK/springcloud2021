package com.paul.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author paul
 * @Description
 * @create 2021-11-09 21:25
 */
public interface LoadBalance {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
