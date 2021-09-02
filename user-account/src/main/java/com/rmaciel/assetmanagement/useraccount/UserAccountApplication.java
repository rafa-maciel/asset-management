package com.rmaciel.assetmanagement.useraccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.rmaciel.academy.core.models")
@EnableJpaRepositories("com.rmaciel.academy.core.repositories")
@ComponentScan({"com.rmaciel.academy.token", "com.rmaciel.assetmanagement.useraccount"})
public class UserAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccountApplication.class, args);
	}

}
