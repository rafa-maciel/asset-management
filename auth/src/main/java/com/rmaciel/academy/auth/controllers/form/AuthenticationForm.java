package com.rmaciel.academy.auth.controllers.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AuthenticationForm {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
