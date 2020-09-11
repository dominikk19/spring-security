package com.example.springsecurity;

import com.example.springsecurity.domain.usecases.user.Holder;
import com.example.springsecurity.domain.usecases.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
        final UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.save(new Holder("admin", "admin", List.of("ROLE_ADMIN")));
    }

}
