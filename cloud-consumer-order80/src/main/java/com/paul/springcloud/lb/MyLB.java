package com.paul.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author paul
 * @Description
 * @create 2021-11-09 21:27
 */
@Component
public class MyLB implements LoadBalance {

    private AtomicInteger atomicInteger = new  AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        // 自旋锁
        do {
            current = this.atomicInteger.get();
            // 如果当前值和期望值一致，就修改
            next = current >= 2147483647 ? 0 : current + 1;
            /**
             * 关键点，当前值和期望值比较，如果是修改就跳出循环
             * this.atomicInteger.compareAndSet(current, next)
             */
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("----访问的次数next:" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}