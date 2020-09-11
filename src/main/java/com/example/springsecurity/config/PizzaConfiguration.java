package com.example.springsecurity.config;

import com.example.springsecurity.domain.usecases.pizza.PizzaRepository;
import com.example.springsecurity.domain.usecases.pizza.PizzaService;
import com.example.springsecurity.domain.usecases.pizza.PizzaServiceImpl;
import com.example.springsecurity.domain.usecases.user.UserRepository;
import com.example.springsecurity.infrastructure.pizza.InMemoryPizzaRepository;
import com.example.springsecurity.infrastructure.pizza.JpaPizzaRepository;
import com.example.springsecurity.infrastructure.pizza.SpringPizzaRepository;
import com.example.springsecurity.infrastructure.user.UserRepositoryInMemory;
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

    @Bean
    UserRepository userRepository(){
        return new UserRepositoryInMemory();
    }

}
