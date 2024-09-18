package com.employeemanagementsystem.services.impl;

import com.employeemanagementsystem.model.Role;
import com.employeemanagementsystem.repositories.RoleRepo;
import com.employeemanagementsystem.services.RoleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleModelServiceImpl implements RoleModelService {

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public Role createRole(Role role) {
        return roleRepo.save(role);

    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public Role updateRole(Long id, Role role) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles;
    }

    @Override
    public Optional<Role> getRole(Long roleID) {
        return roleRepo.findById(roleID);
    }
}
