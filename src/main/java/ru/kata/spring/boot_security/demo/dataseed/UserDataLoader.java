package ru.kata.spring.boot_security.demo.dataseed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;
import ru.kata.spring.boot_security.demo.repo.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (roleRepository.count() == 0) {
            Role role1 = new Role();
            role1.setName("ROLE_ADMIN");
            Role role2 = new Role();
            role2.setName("ROLE_USER");
            roleRepository.save(role1);
            roleRepository.save(role2);
        }

        if (userRepository.count() == 0) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = "admin";
            String encodePass = passwordEncoder.encode(password);
            System.out.println(encodePass);

            User user1 = new User("admin", encodePass, "Alexander", "Martynets", 18);
            Set<Role> roles = user1.getRoles();//new HashSet<>();
            Role role = roleRepository.findByName("ROLE_ADMIN");
            roles.add(role);
            user1.setRoles(roles);
            userRepository.save(user1);
        }

        System.out.println(userRepository.count());
    }
}
