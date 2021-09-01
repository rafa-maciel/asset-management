package com.rmaciel.academy.token.service;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.token.config.TokenProperties;
import com.rmaciel.academy.token.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Slf4j
public class TokenService {
    private final TokenProperties tokenProperties;

    public TokenService(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    public TokenDTO convertToDTO(UserAccount userAccount) {
        Date currentDatetime = new Date();
        Date expirationDatetime = new Date(currentDatetime.getTime() + tokenProperties.getExpirationTime());

        String token = Jwts.builder()
                .setIssuer("Cloud Gateway Example")
                .setSubject(userAccount.getId().toString())
                .setIssuedAt(currentDatetime)
                .setExpiration(expirationDatetime)
                .signWith(SignatureAlgorithm.HS256, tokenProperties.getJwtSecret())
                .compact();

        return new TokenDTO(token, tokenProperties.getType(), new Timestamp(expirationDatetime.getTime()).toLocalDateTime());
    }

    public boolean isValid(String token) {
        token = formatToken(token);
        return this.getTokenClaims(token) != null;
    }

    public Long getUniqueKeyFrom(String token) {
        token = formatToken(token);

        Claims tokenClaims = getTokenClaims(token);
        return tokenClaims != null ? Long.parseLong(tokenClaims.getSubject()) : null;
    }

    private Claims getTokenClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(tokenProperties.getJwtSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info(token);
            log.warn(e.getMessage());
            return null;
        }
    }

    private String formatToken(String token) {
        if (token == null || token.isEmpty())
            return null;
        if (token.startsWith("Bearer"))
            token = token.substring(7);

        return token;
    }

    public String getValidTokenOrNull(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = formatToken(header);
        return isValid(token) ? token : null;
    }
}
