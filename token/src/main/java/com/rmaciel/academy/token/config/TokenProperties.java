package com.rmaciel.academy.token.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "token")
@Data
public class TokenProperties {
    private final String type = "Bearer";
    // 10 Hours
    private final Long expirationTime = 60 * 1000L * 60 * 10;
    private final String jwtSecret = "1asd63f5as4df65";
}
