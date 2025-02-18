package com.demo.service.configuration.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigServer
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.demo")
@SpringBootApplication
public class ServiceConfigurationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConfigurationManagerApplication.class, args);
	}

}
