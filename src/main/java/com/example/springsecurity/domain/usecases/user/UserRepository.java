package com.example.springsecurity.domain.usecases.user;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project spring-security
 * @date 11.09.2020
 */
public interface UserRepository {
    void save(Holder user);
    Optional<Holder> findByUsername(String username);
}
