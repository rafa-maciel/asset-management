package com.rmaciel.academy.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldErrorMessageDTO {
    private String name;
    private String message;
}
