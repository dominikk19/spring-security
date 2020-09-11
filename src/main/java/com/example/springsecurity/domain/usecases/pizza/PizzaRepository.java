package com.example.springsecurity.domain.usecases.pizza;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
public interface PizzaRepository {

    Pizza save(Pizza pizza);
    Optional<Pizza> findOne(Long id);
    void delete(Long id);
}
