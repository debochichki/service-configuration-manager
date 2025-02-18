package com.demo.service.configuration.manager.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import com.demo.service.configuration.manager.model.ApplicationConfiguration;
import com.demo.service.configuration.manager.repository.ApplicationConfigurationsRepository;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationConfigurationsService {
    
    private final ApplicationConfigurationsRepository repository;
    private final ConfigurationRefreshService refreshService;
    
    public List<ApplicationConfiguration> getAllConfigurations() {
        return repository.fetchAll();
    }
    
    public ApplicationConfiguration saveConfiguration(final String application,
                                                      final String profile,
                                                      final Map<String, String> configurationEntries) {
        repository.saveConfiguration(application, profile, configurationEntries);
        
        refreshService.refreshConfiguration(application);
        
        return repository.fetchByApplicationAndProfile(application, profile)
            .orElseThrow(() -> new RuntimeException("Entity not found."));
    }
    
}
