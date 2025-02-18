package com.demo.service.configuration.manager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.demo.service.configuration.manager.client.ServiceConfigurationRefreshApi;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Service;

import feign.Feign;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigurationRefreshService {
    
    private static final String SVC_SUFFIX = "-svc";
    
    private final DiscoveryClient discoveryClient;
    
    public void refreshConfiguration(final String applicationName) {
        try {
            final List<ServiceInstance> instances = discoveryClient.getInstances(applicationName + SVC_SUFFIX);
            log.info("Instances count {}", instances.size());
            
            instances.stream()
                .map(i -> i.getHost() + ':' + i.getPort())
                .peek(url -> log.info("URL - {}", url))
                .forEach(url -> Feign.builder().contract(new SpringMvcContract())
                    .target(ServiceConfigurationRefreshApi.class, "http://" + url + '/')
                    .refreshApplicationConfiguration());
        } catch (final RuntimeException e) {
            log.error("Failed to refresh application [{}]", applicationName, e);
            throw e;
        }
    }
}
