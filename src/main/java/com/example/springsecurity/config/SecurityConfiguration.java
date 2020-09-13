package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests(authorizeRequest -> authorizeRequest
                        .antMatchers(HttpMethod.POST, "/pizza").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/test", "/pizza/").hasRole("USER")
                        .anyRequest()
                        .permitAll());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(standardPasswordEncoder().encode("admin")).authorities(List.of(new SimpleGrantedAuthority("ADMIN")))
                .and()
                .withUser("user")
                .password(pbkdf2PasswordEncoder().encode("password")).authorities(List.of(new SimpleGrantedAuthority("USER")));
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("string", standardPasswordEncoder());
        encoders.put("pbkdf2", pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder());
        return delegatingPasswordEncoder;
    }

    private PasswordEncoder standardPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    private PasswordEncoder pbkdf2PasswordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }


}
