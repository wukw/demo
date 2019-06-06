package com.wukw.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {
    public static void main(String[] args) {
        new SpringApplication(WebApp.class).run(args);
    }
}
