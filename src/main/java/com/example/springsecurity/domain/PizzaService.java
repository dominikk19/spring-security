package com.example.springsecurity.domain;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
public interface PizzaService {
    Pizza save(Pizza pizza);

    Pizza findOne(Long id);

    void delete(Long id);
}
