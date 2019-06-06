package com.wukw.consul.controller;

import com.orbitz.consul.model.health.ServiceHealth;
import com.wukw.consul.consulclient.ConsulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("consul")
@RestController
public class ConsulController {
    @Autowired
    ConsulService consulService;

    @GetMapping("findHealth/{name}")
    public List<ServiceHealth> findHealth(@PathVariable("name") String name) {
        List<ServiceHealth> object =  consulService.findHealth(name);
        return object;
    }
}
