package com.plantacion.employeemanagementapp.serviceImpl;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.dto.AppUserDTO;
import com.plantacion.employeemanagementapp.repository.AppUserRepository;
import com.plantacion.employeemanagementapp.repository.RoleRepository;
import com.plantacion.employeemanagementapp.service.AppUserService;
import com.plantacion.employeemanagementapp.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public AppUser createUser(AppUserDTO user) {
        AppUser appUser = AppUser.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .role(roleService.findAllByIds(user.getUserRolesId()))
                .build();
        return repository.save(appUser);
    }

}
