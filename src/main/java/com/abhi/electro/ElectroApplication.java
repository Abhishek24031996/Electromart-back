package com.abhi.electro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EntityScan(basePackages = "com.abhi.electro.entity")  // Entities
@EnableJpaRepositories(basePackages = "com.abhi.electro.repository")
@ComponentScan(basePackages={
		"com.abhi.electro.controller",
		"com.abhi.electro.config",
		"com.abhi.electro.filter",
		"com.abhi.electro.services.jwt",
		"com.abhi.electro.utils",
		"com.abhi.electro.services.jwt.auth"})
public class ElectroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectroApplication.class, args);
	}
	

}
