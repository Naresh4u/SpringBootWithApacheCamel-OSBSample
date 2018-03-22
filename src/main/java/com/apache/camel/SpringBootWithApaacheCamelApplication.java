package com.apache.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootWithApaacheCamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithApaacheCamelApplication.class, args);
	}
}
