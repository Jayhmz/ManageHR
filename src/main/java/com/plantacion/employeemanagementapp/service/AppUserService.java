package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;

public interface AppUserService {
    AppUser createUser(AppUserDTO user);
}
