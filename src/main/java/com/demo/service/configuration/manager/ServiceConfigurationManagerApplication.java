package com.demo.service.configuration.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ServiceConfigurationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConfigurationManagerApplication.class, args);
	}

}
