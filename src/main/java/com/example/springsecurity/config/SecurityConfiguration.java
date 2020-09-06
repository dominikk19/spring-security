package com.example.springsecurity.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String MSG_UNAUTHENTICATED = "Unauthenticated";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String BASIC = "Basic";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequest -> {
                    authorizeRequest
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic(httpBasic -> {
                    httpBasic.authenticationEntryPoint((request, response, authenticationException) -> {
                        response.setStatus(401);
                        response.setHeader(WWW_AUTHENTICATE, BASIC);
                        response.getWriter().println(MSG_UNAUTHENTICATED);
                    });
                });

    }
}
