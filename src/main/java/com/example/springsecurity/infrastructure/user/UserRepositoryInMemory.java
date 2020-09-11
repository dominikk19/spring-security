package com.example.springsecurity.infrastructure.user;

import com.example.springsecurity.domain.usecases.user.Holder;
import com.example.springsecurity.domain.usecases.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 11.09.2020
 */
public class UserRepositoryInMemory implements UserRepository {

    private final List<Holder> users = new ArrayList<>();

    public void save(Holder user){
        users.add(user);
    }

    public Optional<Holder> findByUsername(String username){
       return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
