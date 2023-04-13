package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /*@GetMapping()
    public String admin(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "admin";
    }*/
    //вывод
    @GetMapping(value = "")
    public String printAllUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("listUsers", users);
        return "admin";
    }
    //создание
    @GetMapping("/create_user")
    public String createUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admin/create_user";
    }
    //сохранение
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }
    //удаление
    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    //редактирование
    @GetMapping("/edit_user")
    public String editUser (@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit_user";
    }
    //сохранение изменений
    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user,
                          @RequestParam("id") long id){
        userService.editUser(user);
        return "redirect:/admin";
    }
    //инфо
    @GetMapping("/info")
    public String getUser (@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/info";
    }
}
