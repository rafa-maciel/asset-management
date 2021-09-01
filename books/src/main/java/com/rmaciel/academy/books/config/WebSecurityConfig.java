package com.rmaciel.academy.books.config;

import com.rmaciel.academy.token.filter.TokenRequestFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenRequestFilter tokenFilter;

    public WebSecurityConfig(TokenRequestFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.GET, "/books/all").hasAnyRole("DEV", "ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
