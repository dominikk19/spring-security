package com.example.springsecurity.domain.usecases.pizza;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Pizza {

    private Long id;
    private String name;
}
