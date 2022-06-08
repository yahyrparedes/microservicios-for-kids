package com.killerypa.products.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component("EurekaClient")
public class EurekaClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public URI getUri(String serviceID) {
        List<ServiceInstance> list =
                discoveryClient.getInstances(serviceID);
        if (list != null && list.size() > 0) {
            return list.get(0).getUri();
        }
        return null;
    }



}
