package com.springboot.amazonsqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication annotation enables the auto-configuration feature of the spring boot module (i.e. java-based configuration and component scanning).
@SpringBootApplication
public class SpringbootSqsTutorial {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootSqsTutorial.class);

	public static void main(String[] args) {
		// The "run()" method returns the "ConfigurableApplicationContext" instance which can be further used by the spring application.
		SpringApplication.run(SpringbootSqsTutorial.class, args);
		LOGGER.info("Springboot with amazonsqs application started successfully.");
	}
}
