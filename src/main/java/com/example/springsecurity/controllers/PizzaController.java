package com.example.springsecurity.controllers;

import com.example.springsecurity.domain.usecases.pizza.Pizza;
import com.example.springsecurity.domain.usecases.pizza.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@RequiredArgsConstructor
@RequestMapping(PizzaController.PREFIX_PIZZA_PATH)
@RestController
class PizzaController {
    public static final String PREFIX_PIZZA_PATH = "/pizza";

    private final PizzaService pizzaService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Pizza> createNewPizza(@NotNull @RequestBody Pizza pizza) {
        return ResponseEntity.ok(pizzaService.save(pizza));
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Pizza> findPizza(@PathVariable Long id) {
        return ResponseEntity.ok(pizzaService.findOne(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        pizzaService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
