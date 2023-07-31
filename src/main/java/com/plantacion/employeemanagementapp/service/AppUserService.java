package com.plantacion.employeemanagementapp.service;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import org.springframework.data.domain.Page;

public interface AppUserService {
    AppUser createUser(AppUserDTO user);

    Page<AppUser> getPagenatedUsers(int pageNumber, int pageSize);
}
