package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> fetchAllRoles();
    Optional<Role> findbyId(int id);
    List<Role> findAllByIds(List<Integer> ids);
}
