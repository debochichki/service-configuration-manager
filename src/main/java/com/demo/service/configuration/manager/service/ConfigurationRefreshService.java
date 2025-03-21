package com.demo.service.configuration.manager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.event.PathDestinationFactory;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigurationRefreshService {
    
    private final ApplicationEventPublisher eventPublisher;
    @Value("${spring.cloud.bus.id}")
    private String busId;
    
    public void refreshConfiguration(final String applicationName) {
        final var refreshEvent = new RefreshRemoteApplicationEvent(this,
            busId,
            new PathDestinationFactory().getDestination(applicationName));
        eventPublisher.publishEvent(refreshEvent);
    }
}
