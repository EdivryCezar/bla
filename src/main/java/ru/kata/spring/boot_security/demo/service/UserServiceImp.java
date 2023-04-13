package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.UserRepo;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepo userRepo, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepo.getById(id);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void editUser(User updateUser) {

        if (!updateUser.getPassword().equals(getUserById(updateUser.getId()).getPassword())){
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        userRepo.save(updateUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }
}
