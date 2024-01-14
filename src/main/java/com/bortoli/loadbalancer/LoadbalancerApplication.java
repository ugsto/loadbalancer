package com.bortoli.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bortoli.loadbalancer.config.ConfigLoader;
import com.bortoli.loadbalancer.logger.ApplicationLogger;

@SpringBootApplication
public class LoadbalancerApplication {

	public static void main(String[] args) {
		ApplicationLogger.info("Starting load balancer...");
		ApplicationLogger.debug("Config: " + ConfigLoader.load());

		SpringApplication.run(LoadbalancerApplication.class, args);
	}
}
