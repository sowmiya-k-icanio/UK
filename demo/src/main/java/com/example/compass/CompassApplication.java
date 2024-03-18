/*
 * Copyright (c) Icanio
 */

package com.example.compass;

import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "Compass API", version = "1.0", description = "Compass Information"))
public class CompassApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompassApplication.class, args);
	}


}
