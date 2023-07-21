package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
