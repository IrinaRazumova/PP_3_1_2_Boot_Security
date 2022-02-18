package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    User create();
    User add(User user);
    void delete(User user);
    void deleteById(int id);
    User edit(User user);
    User getById(int id);
    User getByName(String name);
    Iterable<Role> allRoles();
}
