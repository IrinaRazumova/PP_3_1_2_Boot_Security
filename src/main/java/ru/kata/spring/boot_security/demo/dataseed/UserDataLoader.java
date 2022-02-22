package ru.kata.spring.boot_security.demo.dataseed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

@Component
@Transactional
public class UserDataLoader implements CommandLineRunner {
    private UserDao userDao;
    private RoleDao roleDao;

    public UserDataLoader(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    private void loadUserData() {
        if (roleDao.getAll().isEmpty()) {
            Role role1 = new Role();
            role1.setName("ROLE_ADMIN");
            Role role2 = new Role();
            role2.setName("ROLE_USER");
            roleDao.add(role1);
            roleDao.add(role2);
        }

        if (userDao.getAllUsers().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = "admin";
            String encodePass = passwordEncoder.encode(password);
            //System.out.println(encodePass);

            User user1 = new User("admin", encodePass, "Alexander", "Martynets", 18);
            Set<Role> roles = user1.getRoles();//new HashSet<>();
            Role role1 = roleDao.getByName("ROLE_ADMIN");
            roles.add(role1);
            Role role2 = roleDao.getByName("ROLE_USER");
            roles.add(role2);
            user1.setRoles(roles);
            userDao.add(user1);

            String password2 = "user";
            String encodePass2 = passwordEncoder.encode(password2);
            //System.out.println(encodePass);

            User user2 = new User("user", encodePass2, "Timur", "Bro", 18);
            Set<Role> roles2 = user2.getRoles();//new HashSet<>();
            roles2.add(role2);
            user2.setRoles(roles2);
            userDao.add(user2);
        }
    }
}
