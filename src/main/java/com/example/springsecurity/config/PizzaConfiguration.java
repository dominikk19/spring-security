package com.example.springsecurity.config;

import com.example.springsecurity.domain.PizzaRepository;
import com.example.springsecurity.domain.PizzaService;
import com.example.springsecurity.domain.PizzaServiceImpl;
import com.example.springsecurity.infrastructure.InMemoryPizzaRepository;
import com.example.springsecurity.infrastructure.JpaPizzaRepository;
import com.example.springsecurity.infrastructure.SpringPizzaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 06.09.2020
 */
@Configuration
public class PizzaConfiguration {


    public PizzaService pizzaServiceInMemory() {
        return pizzaService(new JpaPizzaRepository(new InMemoryPizzaRepository()));
    }

    @Bean
    PizzaService pizzaService(PizzaRepository pizzaRepository) {
        return new PizzaServiceImpl(pizzaRepository);
    }

    @Bean
    PizzaRepository pizzaRepository(SpringPizzaRepository springPizzaRepository){
        return new JpaPizzaRepository(springPizzaRepository);
    }

    @Bean
    SpringPizzaRepository springPizzaRepository(){
        return new InMemoryPizzaRepository();
    }

}
