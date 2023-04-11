package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepo;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImp(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Override
    public Role getRoleById(Long id) {
        return roleRepo.getById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
