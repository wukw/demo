package com.wukw.spring.consul.controller;

import com.wukw.spring.consul.aspect.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("test")
public class TestController {
    @Value("${spring.cloud.consul.discovery.health-check-path}")
    String path;

    @Autowired
    RestTemplate restTemplate;


    @Role(role = "admin")
    @GetMapping("getPath")
    public String getPath() {
        return path;
    }

    @GetMapping("health")
    public String health() {
        return restTemplate.getForObject("http://consul-web-client/foo/actuator/health", String.class);
    }
}
