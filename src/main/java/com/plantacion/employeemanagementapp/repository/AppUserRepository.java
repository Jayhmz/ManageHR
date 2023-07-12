package com.plantacion.employeemanagementapp.repository;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
}
