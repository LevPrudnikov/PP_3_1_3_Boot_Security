package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    void save(User user);

    Optional<User> findById(Long id);

    User findByUsername(String username);

    void deleteById(Long id);

    void updateUser(User user);
}
