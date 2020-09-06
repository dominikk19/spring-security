package com.example.springsecurity.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PizzaData {

    private Long id;
    private String name;

    void setId(Long id) {
        this.id = Optional.ofNullable(this.id)
                .orElse(id);

    }
}

