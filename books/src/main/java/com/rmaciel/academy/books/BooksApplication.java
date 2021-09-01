package com.rmaciel.academy.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.rmaciel.academy.core.models")
@EnableJpaRepositories("com.rmaciel.academy.core.repositories")
@ComponentScan({"com.rmaciel.academy.books", "com.rmaciel.academy.token"})
@EnableEurekaClient
public class BooksApplication {
	public static void main(String[] args) {
		SpringApplication.run(BooksApplication.class, args);
	}
}
