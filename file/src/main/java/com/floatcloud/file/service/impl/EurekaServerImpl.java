package com.floatcloud.file.service.impl;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * 获取 Eureka 服务列表
 * @author floatcloud
 */
@Service
public class EurekaServerImpl {

    @Resource
    private DiscoveryClient discoveryClient;


    public List<ServiceInstance> getEurekaServerList(){
        List<ServiceInstance> result = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        services.forEach(serverName->{
            List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
            instances.forEach(serviceInstance -> {
                result.add(serviceInstance);
            });
        });
        return result;
    }

}
