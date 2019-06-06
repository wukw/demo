package com.wukw.consul.consulclient;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.health.ServiceHealth;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ConsulService implements InitializingBean {
    HostAndPort hostAndPort = HostAndPort.fromParts("127.0.0.1", 8500);
    Consul consul = Consul.builder().withHostAndPort(hostAndPort).build();
    AgentClient agentClient = consul.agentClient();

    public void registerService(String serviceName, String serviceId) {

        try {
            //注册 health接口
            agentClient.register(8099, URI.create("http://127.0.0.1:8099/actuator/health").toURL(), 30l, serviceName, serviceId, Arrays.asList("dev"), new HashMap<>());
            System.out.println("注册成功");
        } catch (MalformedURLException e) {
            System.out.println("注册失败");
        }


    }

    public List<ServiceHealth> findHealth(String servicename) {
        HealthClient healthClient = consul.healthClient();
        return healthClient.getHealthyServiceInstances(servicename).getResponse();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.registerService("wukw", "wukw1");

    }
}
