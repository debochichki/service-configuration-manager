package com.demo.service.configuration.manager.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.demo.service.configuration.manager.model.ApplicationConfiguration;

public interface ApplicationConfigurationsRepository {
    
    List<ApplicationConfiguration> fetchAll();
    
    Optional<ApplicationConfiguration> fetchByApplicationAndProfile(String application, String profile);
    
    void saveConfiguration(String application, String profile, Map<String, String> configurations);
}
