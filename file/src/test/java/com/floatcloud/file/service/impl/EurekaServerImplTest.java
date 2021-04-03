package com.floatcloud.file.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

import javax.annotation.Resource;

@SpringBootTest
public class EurekaServerImplTest {

    @Resource
    EurekaServerImpl eurekaServer;

    @Test
    public void testGetEurekaServerList(){
        List<ServiceInstance> eurekaServerList = eurekaServer.getEurekaServerList();
        eurekaServerList.stream().forEach(serviceInstance -> {
            System.out.printf("%s:%s:%s:%s:%s", serviceInstance.getHost(),
                    serviceInstance.getPort(),serviceInstance.getUri(),
                    serviceInstance.getUri(),serviceInstance.getInstanceId() + "/n");
        });
    }
}
