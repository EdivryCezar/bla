package ru.kata.spring.boot_security.demo.controllers;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private List<User> users;
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String helloPage(ModelMap model) {
        return "index";
    }

    @GetMapping(value = "/user")
    public String userPage(ModelMap model) {
        return "user";
    }

    @GetMapping(value = "/admin")
    public String printAllUsers(ModelMap model) {
        users = service.listUsers();
        model.addAttribute("listUsers", users);
        return "admin";
    }

    @GetMapping("/admin/create_user")
    public String createUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/create_user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete_user")
    public String deleteUser(@RequestParam("id") long id) {
        service.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit_user")
    public String editUser (@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", service.getUserById(id));
        return "admin/edit_user";
    }
    @PostMapping("/{id}")
    public String update (@ModelAttribute("user") User user,
                          @RequestParam("id") long id){
        service.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String getById(@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", service.getUserById(id));
        return "admin/info";
    }

/*
    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return users.stream().filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }*/
}