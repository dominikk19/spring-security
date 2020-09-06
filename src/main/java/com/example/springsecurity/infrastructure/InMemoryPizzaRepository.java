package com.example.springsecurity.infrastructure;

import lombok.extern.log4j.Log4j2;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@Log4j2
public class InMemoryPizzaRepository implements SpringPizzaRepository {

    private static String NEW_PIZZA_IS_SAVED_WITH_ID = "New Pizza is saved with id: s%";
    private ConcurrentHashMap<Long, PizzaData> map = new ConcurrentHashMap<>();

    @Override
    public PizzaData save(PizzaData pizza) {
        requireNonNull(pizza);
        Long id = pizza.getId();
        if (id == null) {
            id = map.size() + 1L;
            pizza.setId(id);
            map.put(id, pizza);
            log.info(String.format(NEW_PIZZA_IS_SAVED_WITH_ID, id));
        } else {
            map.put(pizza.getId(), pizza);
        }
        return map.get(id);
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
