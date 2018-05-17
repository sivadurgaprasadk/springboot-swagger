package com.boot.swagger.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.boot.swagger.config.SwaggerConfig;

@SpringBootApplication(scanBasePackages = {"com.boot.swagger.controller", "com.boot.swagger.service"})
@Import(value = {SwaggerConfig.class})
public class SpringDataMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMongoDbApplication.class, args);
	}
}
