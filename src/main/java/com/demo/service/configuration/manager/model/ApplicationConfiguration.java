package com.demo.service.configuration.manager.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO holding Application Configuration details
 *
 * @author dimitar.debochichki
 */
@Data
public class ApplicationConfiguration {
    
    private long id;
    
    private LocalDateTime creationTimestamp;
    
    private String application;
    
    private String profile;
    
    private Map<String, String> value;
}
