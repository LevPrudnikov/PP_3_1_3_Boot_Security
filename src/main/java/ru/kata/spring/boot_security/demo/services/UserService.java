package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void addUser(User user);

    User getUserById(Long id);

    User findByUsername(String username);

    void deleteUser(Long id);

    void updateUser(User user);
}
