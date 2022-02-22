package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role getByName(String role_user);
    List<Role> getAll();
    void add(Role role);
    Role getById(int id);
    Set<Role> getRolesByName(Set<Role> roles);
}
