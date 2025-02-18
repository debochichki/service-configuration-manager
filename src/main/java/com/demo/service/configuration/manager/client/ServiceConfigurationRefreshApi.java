package com.demo.service.configuration.manager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "service-configuration-refresh-api", url = "NOT_USED")
public interface ServiceConfigurationRefreshApi {
    
    @PostMapping("/actuator/refresh")
    void refreshApplicationConfiguration();
}
