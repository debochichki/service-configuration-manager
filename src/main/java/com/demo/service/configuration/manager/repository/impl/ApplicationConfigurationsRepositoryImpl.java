package com.demo.service.configuration.manager.repository.impl;


import static com.demo.jooq.generated.tables.ApplicationConfigurations.APPLICATION_CONFIGURATIONS;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.demo.service.configuration.manager.model.ApplicationConfiguration;
import com.demo.service.configuration.manager.repository.ApplicationConfigurationsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jooq.DSLContext;
import org.jooq.JSON;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ApplicationConfigurationsRepositoryImpl implements ApplicationConfigurationsRepository {

    private final DSLContext context;
    private final ObjectMapper objectMapper;

    @Override
    public List<ApplicationConfiguration> fetchAll() {
        return context.selectFrom(APPLICATION_CONFIGURATIONS).fetchInto(ApplicationConfiguration.class);
    }
    
    @Override
    public Optional<ApplicationConfiguration> fetchByApplicationAndProfile(final String application, final String profile) {
        return context.selectFrom(APPLICATION_CONFIGURATIONS)
            .where(APPLICATION_CONFIGURATIONS.APPLICATION.eq(application)
                .and(APPLICATION_CONFIGURATIONS.PROFILE.eq(profile)))
            .fetchOptionalInto(ApplicationConfiguration.class);
    }
    
    @Override
    public void saveConfiguration(final String application, final String profile, final Map<String, String> configurations) {
        try {
            context.insertInto(APPLICATION_CONFIGURATIONS)
                .set(APPLICATION_CONFIGURATIONS.APPLICATION, application)
                .set(APPLICATION_CONFIGURATIONS.PROFILE, profile)
                .set(APPLICATION_CONFIGURATIONS.VALUE, JSON.json(objectMapper.writeValueAsString(configurations)))
                .onDuplicateKeyUpdate()
                .set(APPLICATION_CONFIGURATIONS.CREATION_TIMESTAMP, LocalDateTime.now())
                .set(APPLICATION_CONFIGURATIONS.APPLICATION, application)
                .set(APPLICATION_CONFIGURATIONS.PROFILE, profile)
                .set(APPLICATION_CONFIGURATIONS.VALUE, JSON.json(objectMapper.writeValueAsString(configurations)))
                .execute();
        } catch (final JsonProcessingException e) {
            log.error("Failed save configuration for application [{}], profile [{}], configurations {}", application, profile, configurations);
            throw new RuntimeException(e);
        }
    }
}
