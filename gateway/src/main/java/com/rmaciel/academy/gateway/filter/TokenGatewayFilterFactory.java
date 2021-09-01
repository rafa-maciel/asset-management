package com.rmaciel.academy.gateway.filter;

import com.rmaciel.academy.token.service.TokenService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.TokenConfig> {

    private final TokenService tokenService;

    public TokenGatewayFilterFactory(TokenService tokenService) {
        super(TokenConfig.class);
        this.tokenService = tokenService;
    }

    @Override
    public GatewayFilter apply(TokenConfig config) {
        return (exchange, chain) -> {
            log.info("Token service filter has been called");
            String token = (String) exchange.getRequest().getHeaders().getFirst("Authorization");

            log.info("Token: " + token);
            if (token == null || token.isEmpty() || !tokenService.isValid(token)) {
                log.info("Token is not valid");
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            return chain.filter(exchange);
        };
    }

    @Data
    @NoArgsConstructor
    public static class TokenConfig {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
