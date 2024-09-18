package com.employeemanagementsystem.services;

import com.employeemanagementsystem.model.Role;

import java.util.List;
import java.util.Optional;


public interface RoleModelService {

    Role createRole(Role role);

    void deleteRole(Long id);

    Role updateRole(Long id, Role role);

    List<Role> getAllRoles();

    Optional<Role> getRole(Long roleID);

}
