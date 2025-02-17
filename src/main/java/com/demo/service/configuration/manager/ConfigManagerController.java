package com.demo.service.configuration.manager;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import com.demo.service.configuration.manager.model.ApplicationConfiguration;
import com.demo.service.configuration.manager.service.ApplicationConfigurationsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConfigManagerController.BASE_RESOURCE_PATH + "/application-configurations")
@AllArgsConstructor
public class ConfigManagerController {
    
    public static final String BASE_RESOURCE_PATH = "/configuration-manager";
    
    private final ApplicationConfigurationsService service;
    
    @GetMapping
    public List<ApplicationConfiguration> getAllConfigurations() {
        return service.getAllConfigurations();
    }
    
    @PutMapping("/{application}/{profile}")
    public ApplicationConfiguration saveConfiguration(@PathVariable final String application,
                                                      @PathVariable final String profile,
                                                      @RequestBody final Map<String, String> configurationEntries) {
        return service.saveConfiguration(application, profile, configurationEntries);
    }
}
