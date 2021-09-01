package com.rmaciel.academy.token.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private String type;
    private LocalDateTime timeExpiration;
}
