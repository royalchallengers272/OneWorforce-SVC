package com.learning.oneworforce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.learning.oneworforce.controller.LoginController;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@SpringBootApplication
public class OneworforceApplication  {
	public static void main(String[] args) {
		SpringApplication.run(OneworforceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("OneWorkForce").version("1.0").description("Portal for Employee needs")
			.termsOfService("http://swagger.io/terms/")
			.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
