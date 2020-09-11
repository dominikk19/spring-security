package com.example.springsecurity.infrastructure;

import lombok.extern.log4j.Log4j2;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@Log4j2
public class InMemoryPizzaRepository implements SpringPizzaRepository {

    private static final String OBJECT_CAN_N0T_BE_NULL = "Object can't be null";
    private static String NEW_PIZZA_IS_SAVED_WITH_ID = "New Pizza is saved with id: %s";
    private ConcurrentHashMap<Long, PizzaData> map = new ConcurrentHashMap<>();

    @Override
    public PizzaData save(PizzaData pizzaData) {
        return Optional.ofNullable(pizzaData)
                .map(pizza -> {
                    Optional.ofNullable(pizza.getId())
                            .ifPresentOrElse(id -> map.put(id, pizza),
                                    () -> {
                                        var newId = map.size() + 1L;
                                        pizza.setId(newId);
                                        map.put(newId, pizza);
                                        log.info(String.format(NEW_PIZZA_IS_SAVED_WITH_ID, newId));
                                    });
                    return pizza;
                })
                .orElseThrow(() -> new IllegalArgumentException(OBJECT_CAN_N0T_BE_NULL));
    }

    @Override
    public Optional<PizzaData> findOne(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
    }
}
