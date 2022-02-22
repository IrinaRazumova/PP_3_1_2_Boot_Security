package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }

    public Set<Role> getRolesByName(Set<Role> roles){
        Set<Role> userRoles = new HashSet<>();
        for (Role role : roles) {
            userRoles.add(getByName(role.getName()));
        }
        return userRoles;
    }

}
