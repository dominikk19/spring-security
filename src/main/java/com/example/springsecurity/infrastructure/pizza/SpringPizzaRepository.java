package com.example.springsecurity.infrastructure.pizza;

import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
public interface SpringPizzaRepository extends Repository<PizzaData, Long> {

    PizzaData save(PizzaData pizza);

    Optional<PizzaData> findOne(Long id);

    void delete(Long id);
}
