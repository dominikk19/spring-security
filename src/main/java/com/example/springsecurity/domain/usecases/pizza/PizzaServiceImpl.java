package com.example.springsecurity.domain.usecases.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */

@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {


    private static final String MSG_PIZZA_NOT_FOUND = "Pizza by id %s not found";
    private final PizzaRepository pizzaRepository;

    @Override
    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza findOne(Long id) {
        return pizzaRepository. findOne(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(MSG_PIZZA_NOT_FOUND, id)));
    }

    @Override
    public void delete(Long id) {
        pizzaRepository.delete(id);
    }
}
