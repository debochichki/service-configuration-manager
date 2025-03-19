package com.demo.service.configuration.manager.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.demo.service.configuration.manager.model.ApplicationConfiguration;

import org.springframework.stereotype.Repository;

/**
 * {@link Repository} for the application configurations persistence layer
 *
 * @author dimitar.debochichki
 */
public interface ApplicationConfigurationsRepository {
    
    /**
     * Retrieve all application configurations from the underlying datastore
     *
     * @return {@link List<ApplicationConfiguration>}
     */
    List<ApplicationConfiguration> fetchAll();
    
    /**
     * Retrieve application configuration on provided application/tuple
     *
     * @param application application name
     * @param profile application active profile
     * @return {@link ApplicationConfiguration}, if found
     */
    Optional<ApplicationConfiguration> fetchByApplicationAndProfile(String application, String profile);
    
    /**
     * Save configuration properties for an application with provided profile
     *
     * @param application application name
     * @param profile spring profile
     * @param configurations the key/value application properties
     */
    void saveConfiguration(String application, String profile, Map<String, String> configurations);
}
