package com.example.springsecurity.config;

import com.example.springsecurity.controllers.TestController;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

    private static final String MSG_UNAUTHENTICATED = "{ \"message\" : \"Unauthenticated\" }";
    private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    private static final String BASIC = "Basic";
    public static final String CONTENT_TYPE = "Content-Type";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests(authorizeRequest -> {
                    authorizeRequest
                            .antMatchers(HttpMethod.GET, TestController.TEST_PATH)
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic(httpBasic -> {
                    httpBasic.authenticationEntryPoint((request, response, authenticationException) -> {
                        response.setStatus(401);
                        response.setHeader(WWW_AUTHENTICATE, BASIC);
                        response.setHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                        response.getWriter().println(MSG_UNAUTHENTICATED);
                    });
                });
    }
}
