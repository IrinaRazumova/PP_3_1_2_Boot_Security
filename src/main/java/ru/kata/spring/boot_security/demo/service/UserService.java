package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User create();
    void add(User user);
    void delete(User user);
    void deleteById(int id);
    void edit(User user);
    User getById(int id);
    Iterable<Role> allRoles();
    User getByUsername(String username);
}
