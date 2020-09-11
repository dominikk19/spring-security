package com.example.springsecurity.domain.usecases.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 11.09.2020
 */
@AllArgsConstructor
@Getter
public class Holder {

    private String username;
    private String password;
    private List<String> roles;
}
