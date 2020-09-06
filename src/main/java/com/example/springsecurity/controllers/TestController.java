package com.example.springsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@RestController
@RequestMapping("/test")
class TestController {

    public static final String SUFFIX_EMAIL_ADMIN_COM = "%s@admin.com";
    public static final String MSG_TEST_ENDPOINT = "Test endpoint for %s";

    @GetMapping
    String test() {
        var username= SecurityContextHolder.getContext().getAuthentication().getName();
        return String.format(MSG_TEST_ENDPOINT, username);
    }

    @GetMapping("/contacts/email")
    String emailContact(Authentication authentication) {//alternative way to get name from security context
        return String.format(SUFFIX_EMAIL_ADMIN_COM, authentication.getName());
    }
}
