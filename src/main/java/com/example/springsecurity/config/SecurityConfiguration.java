package com.example.springsecurity.config;

import com.example.springsecurity.controllers.TestController;
import com.example.springsecurity.domain.usecases.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    protected UserDetailsService userDetailsService() {
        return username -> {
            var holder = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
            return new User(holder.getUsername(), holder.getPassword(), holder.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //only for test
    }
}
