package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User getUserByUsername(String username);
    void addUser(User user);
    List<User> listUsers();
    //User getUser(long id);

    void deleteUser(long id);

    void editUser(User updateUser);
}
