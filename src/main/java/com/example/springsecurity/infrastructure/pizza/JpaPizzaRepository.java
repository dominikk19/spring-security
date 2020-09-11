package com.example.springsecurity.infrastructure.pizza;

import com.example.springsecurity.domain.usecases.pizza.Pizza;
import com.example.springsecurity.domain.usecases.pizza.PizzaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */

@RequiredArgsConstructor
public class JpaPizzaRepository implements PizzaRepository {

    private final SpringPizzaRepository pizzaRepository;

    @Override
    public Pizza save(Pizza pizza) {
        var newPizzaData = PizzaData.builder()
                .name(pizza.getName())
                .id(pizza.getId())
                .build();
        var savedPizza = pizzaRepository.save(newPizzaData);
        return Pizza.builder()
                .name(savedPizza.getName())
                .id(savedPizza.getId())
                .build();
    }

    @Override
    public Optional<Pizza> findOne(Long id) {
        return pizzaRepository.findOne(id)
                .map(pizzaData -> Pizza.builder()
                        .name(pizzaData.getName())
                        .id(pizzaData.getId())
                        .build());
    }

    @Override
    public void delete(Long id) {
        pizzaRepository.delete(id);
    }

    public void save(PizzaData pizzaData) {
    }
}
