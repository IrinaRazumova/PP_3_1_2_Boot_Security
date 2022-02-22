package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User create() {
        User user = new User();
        Role roleUser = roleDao.getByName("ROLE_USER");
        user.addRole(roleUser);
        return user;
    }
    @Override
    public void add(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleDao.getRolesByName(user.getRoles()));
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void deleteById(int id) {
        delete(getById(id));
    }

    @Override
    public void edit(User user) {
        user.setRoles(roleDao.getRolesByName(user.getRoles()));
        userDao.edit(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userDao.getAllUsers().forEach(user -> users.add(user));
        return users;
    }

    public List<Role> allRoles() {
        return (List<Role>) roleDao.getAll();
    }

    @Override
    public User getByUsername(String username) { return userDao.getByUsername(username); }

}
