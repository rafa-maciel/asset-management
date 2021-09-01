package com.rmaciel.academy.token.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token")
@Data
public class TokenProperties {
    private final String type = "Bearer";
    private final Long expirationTime = 60 * 100000L;
    private final String jwtSecret = "1asd63f5as4df65";
}
