package com.demo.service.configuration.manager.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import com.demo.service.configuration.manager.model.ApplicationConfiguration;
import com.demo.service.configuration.manager.repository.ApplicationConfigurationsRepository;

import org.springframework.stereotype.Service;

/**
 * {@link Service} containing business logic for applying configurations
 *
 * @author dimitar.debochichki
 */
@Service
@RequiredArgsConstructor
public class ApplicationConfigurationsService {
    
    private final ApplicationConfigurationsRepository repository;
    private final ConfigurationRefreshService refreshService;
    
    /**
     * Get all application configurations
     *
     * @return {@link List<ApplicationConfiguration>}
     */
    public List<ApplicationConfiguration> getAllConfigurations() {
        return repository.fetchAll();
    }
    
    /**
     * Save configuration properties for an application and provided profile
     *
     * @param application application name
     * @param profile spring profile
     * @param configurationEntries application properties {@link Map}
     * @return the persisted {@link ApplicationConfiguration} entity
     */
    public ApplicationConfiguration saveConfiguration(final String application,
                                                      final String profile,
                                                      final Map<String, String> configurationEntries) {
        repository.saveConfiguration(application, profile, configurationEntries);
        
        refreshService.refreshConfiguration(application);
        
        return repository.fetchByApplicationAndProfile(application, profile)
            .orElseThrow(() -> new RuntimeException("Entity not found."));
    }
    
}
