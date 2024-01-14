package com.bortoli.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bortoli.loadbalancer.config.ConfigLoader;

@SpringBootApplication
public class LoadbalancerApplication {

	public static void main(String[] args) {
		System.out.println(ConfigLoader.load());
		SpringApplication.run(LoadbalancerApplication.class, args);
	}

}
