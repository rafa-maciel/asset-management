package com.rmaciel.academy.auth.controllers;

import com.rmaciel.academy.auth.controllers.form.AuthenticationForm;
import com.rmaciel.academy.token.dto.TokenDTO;
import com.rmaciel.academy.token.security.user.UserAccountDetails;
import com.rmaciel.academy.token.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Validated AuthenticationForm form) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
        try {
            Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
            UserAccountDetails userAccountDetails = (UserAccountDetails) authenticate.getPrincipal();

            TokenDTO tokenDTO = tokenService.convertToDTO(userAccountDetails.getUserAccount());
            return ResponseEntity.ok(tokenDTO);
        } catch (AuthenticationException ex) {
            log.warn(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
