package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;


    /*public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }*/


    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
    @Override
    public void updateUser(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        User user = entityManager.find(User.class, username);
        return user;
    }
}