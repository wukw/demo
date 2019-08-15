package com.wukw.spring.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class routeConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("build route");
        return builder.routes()
                .route(
                        r -> r.path("/consul-web-client/**")
                                .filters(f -> f.stripPrefix(1)
                                        .addRequestHeader("token", "gateway:toekn")
                                        .retry(config -> config.setRetries(3).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
                                //从注册中心 寻找地址
                                .uri("lb://consul-web-client")
                                //.predicate( e -> e.getRequest().getMethodValue() == "a" )
                                .order(0)
                                .id("consul-web-client")

                )
                .build();

    }

}
