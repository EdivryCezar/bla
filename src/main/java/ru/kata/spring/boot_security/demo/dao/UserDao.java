package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void deleteUser(long id);

    List<User> listUsers();

    void addUser(User user);

    User getUser(long id);

    void updateUser(long id, User user);

    User findByUsername(String username);
}
