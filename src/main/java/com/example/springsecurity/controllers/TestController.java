package com.example.springsecurity.controllers;

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

    @GetMapping
    String test(){
        var userame= SecurityContextHolder.getContext().getAuthentication().getName();
        return String.format("Test endpoint for %s", userame);
    }
}
