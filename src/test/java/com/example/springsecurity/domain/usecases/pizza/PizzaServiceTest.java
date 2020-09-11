package com.example.springsecurity.domain.usecases.pizza;

import com.example.springsecurity.config.PizzaConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
class PizzaServiceTest {

    private PizzaService pizzaService = new PizzaConfiguration().pizzaServiceInMemory();


    @Test
    @DisplayName("given new Pizza" +
            "when save " +
            "then return saved Pizza with id"
    )
    void saveNewPizza() {
        var pizzaName = "Margerita";
        var newPizza = Pizza.builder()
                .name(pizzaName)
                .build();
        var savedPizza = pizzaService.save(newPizza);

        assertAll(() -> assertNotNull(savedPizza),
                () -> assertEquals(pizzaName, savedPizza.getName()),
                () -> assertNotNull(savedPizza.getId()));
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }
}
