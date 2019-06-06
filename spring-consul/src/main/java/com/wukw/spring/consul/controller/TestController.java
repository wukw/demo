package com.wukw.spring.consul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @Value("${spring.cloud.consul.discovery.health-check-path}")
    String path;

    @GetMapping("getPath")
    public String getPath() {
        return path;
    }
}
