package com.rmaciel.assetmanagement.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.rmaciel.academy.core.models")
@EnableJpaRepositories("com.rmaciel.academy.core.repositories")
@ComponentScan({"com.rmaciel.academy.token", "com.rmaciel.assetmanagement.file", "com.rmaciel.academy.core.services"})
@EnableSwagger2
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

}
