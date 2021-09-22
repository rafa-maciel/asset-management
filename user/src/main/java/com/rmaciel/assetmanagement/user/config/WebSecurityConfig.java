package com.rmaciel.assetmanagement.user.config;

import com.rmaciel.academy.core.models.UserAccountProfile;
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

    private final String roleAdmin = UserAccountProfile.ADMIN.toString();
    private final String roleIT = UserAccountProfile.IT.toString();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().authorizeRequests()
                    .antMatchers(HttpMethod.DELETE, "/users/*").hasRole(roleAdmin)
                    .anyRequest().hasAnyRole(roleAdmin, roleIT)
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
