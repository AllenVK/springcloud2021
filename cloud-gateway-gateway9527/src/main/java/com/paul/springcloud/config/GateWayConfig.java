package com.paul.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paul
 * @Description
 * @create 2021-11-14 16:59
 */
@Configuration
public class GateWayConfig {

    /**
     * 配置了一个 id 为 route-name 的路由规则，
     * 当访问地址 http://localhost:9527/guonei 时会自动转发到地址：https://news.baidu.com/guonei
     *
     * @param routeLocatorBuilder 路由构建器
     * @return RouteLocator
     */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

        // https://news.baidu.com/guonei

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_mySite", r -> r.path("/guonei")
                .uri("https://news.baidu.com/guonei"))
                .build();

        return routes.build();
    }

}
