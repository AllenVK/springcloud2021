package com.paul.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-01 21:49
 * @Author: paul
 */
@Slf4j
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        /*try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "testB");
        return "------testB";
    }

    /**
     *
     * 测试Sentinel-异常比例：当资源的每秒请求量（QPS）>= 5，并且每秒异常总数占通过量的比值超过阈值（DegradeRule 中的 count）
     *                      之后，资源进入降级状态，即在接下来的时间窗口期（DegradeRule 中的 timeWindow，以s为的单位）之内，
     *                      对于这个方法调用都会自动返回。异常比率的阈值范围是 [0.0, 1.0]，代表 0% - 100%
     *
     * 过程：1. QPS >= 5 异常比例（秒级统计）超过阈值
     *      2. 触发降级（断路器打开）
     *      3. 时间窗口期结束
     *      4. 关闭降级
     *
     *
     * @return
     */
    @GetMapping("/testC")
    public String testC() {

        int age = 10/0;

        log.info("testC 测试异常比例");
        return "------testC";
    }

    /**
     * 测试 Sentinel-RT 降级，平均响应时间
     *
     * 例如：1. 1s内持续进入5个请求，如果平均响应时间 > 阈值(sentinel降级策略中设置的)
     *      2. 触发降级（断路器打开）
     *      3. 等待窗口期结束（sentinel降级策略中色湖之）
     *      4. 窗口期结束后，关闭降级
     *
     * @return
     */
    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "------testD";
    }

    /**
     *
     * 测试 Sentinel-异常数：当资源近 1 分钟的异常数目超过阈值之后会进行熔断。注意由于统计时间窗口是分钟级别，若 timeWindow 小于 60s
     *                     则熔断结束后仍可能再进入熔断状态
     *                     * 时间窗口一定要大于等于 60 秒
     *
     * 流程：1. 异常数（分钟统计）超过阈值
     *      2. 触发降级（断路器打开）
     *      3. 时间窗口结束
     *      4. 关闭降级
     *
     *
     * @return
     */
    @GetMapping("/testE")
    public String testE() {

        int a = 10/0;

        log.info("testE 测试异常数");
        return "------testE";
    }

    /**
     *
     * 测试热点 key 限流规则：热点即经常访问的数据，很多时候我们希望统计或者限制某个热点数据中访问频次最高的TopN数据，
     *                     并对其访问进行限流或者其它操作
     *
     *                     假如在 Sentinel 中把方法testHotKey里面第一个参数设置为热点 key 监控对象，只要 QPS 超过每秒 1 次，
     *                     马上降级处理
     *
     * @SentinelResource：处理的是 Sentinel 中配置的违规情况，有 blockHandler 方法配置的 fallback 做兜底处理
     *
     * 但是类似 RuntimeException：int i = 10/0; 这种 java 的运行时异常，@SentinelResource 是管不到的
     *
     * 总结：@SentinelResource 只管 Sentinel 中配置的异常情况，运行时该报什么错，还是报什么错
     *
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        log.info("testHotKey 测试热点 key");
        return "------testHotKey";
    }

    /**
     * 作为 @SentinelResource 注解的方法的 blockHandler
     * @param p1
     * @param p2
     * @param exception
     * @return
     */
    public String dealHandler_testHotKey(String p1, String p2, BlockException exception) {
        return "block by hotKey";  // 相当于 Hystrix 的 fallback 方法
    }

}
