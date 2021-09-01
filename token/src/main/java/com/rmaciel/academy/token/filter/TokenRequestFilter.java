package com.rmaciel.academy.token.filter;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import com.rmaciel.academy.token.security.user.UserAccountDetails;
import com.rmaciel.academy.token.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class TokenRequestFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserAccountRepository userAccountRepository;

    public TokenRequestFilter(TokenService tokenService, UserAccountRepository userAccountRepository) {
        this.tokenService = tokenService;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Token Request filter has been called");
        String token = tokenService.getValidTokenOrNull(request);

        log.info("Token has been gotten from request: " + token);
        if (token != null)
            authenticate(token);

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        log.info("Authenticating");
        Long key = tokenService.getUniqueKeyFrom(token);
        log.info("Key: " + key);
        Optional<UserAccount> optionalUser = userAccountRepository.findById(key);

        log.info(optionalUser.toString());
        if (optionalUser.isPresent()) {
            log.info("Logged user found");
            UserAccount userAccount = optionalUser.get();
            log.info(userAccount.toString());

            UserAccountDetails principal = new UserAccountDetails(userAccount);

            log.info(principal.getAuthorities().toString());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
