package com.wukw.spring.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class routeConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("build route");
        return builder.routes()
                .route(r -> r.path("/consul-web-client/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://consul-web-client")
                        .order(0)
                        .id("consul-web-client")

                )
                .build();

    }

}
